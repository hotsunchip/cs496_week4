package com.example.cs496_week4.NewItems;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_week4.AdapterListener.PlaceSearchAdapter;
import com.example.cs496_week4.CheckItems.CheckScheduleActivity;
import com.example.cs496_week4.Data.SchedulePlace;
import com.example.cs496_week4.R;
import com.example.cs496_week4.TimePickerFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NewScheduleActivity extends AppCompatActivity implements PlaceSearchAdapter.OnListItemSelectedInterface, TimePickerFragment.OnTimeSetInterface{
    // fields
    private EditText sdName;
    private TextView sdStartDate;
    private TextView sdStartTime;
    private EditText sdPlace;
    private RecyclerView sdPlaceList;
    private static PlaceSearchAdapter searchAdapter;
    private ArrayList<SchedulePlace> placeList;
    public String responseBody;
    private String timeStartString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newschedule);


        Intent intent = getIntent();
        placeList = new ArrayList<>();

        // set toolbar
        TextView title = findViewById(R.id.toolbar_title);
        title.setText("새 약속 만들기");
        Toolbar toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_close_24);
        }

        // connect variables with layout items
        sdName = findViewById(R.id.et_nsd_name);
        sdStartDate = findViewById(R.id.tv_nsd_start_date);
        sdStartTime = findViewById(R.id.tv_nsd_start_time);
        sdPlace = findViewById(R.id.et_nsd_place);
        sdPlaceList = findViewById(R.id.recycler_view_place);

        // add additional works
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        sdStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment mTimePickerFragment = new TimePickerFragment(TimePickerFragment.CURRENT_TIME, NewScheduleActivity.this);
                mTimePickerFragment.show(getSupportFragmentManager(), TimePickerFragment.FRAGMENT_TAG);
            }
        });
        sdPlace.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    findPlaceUsingKeyword(sdPlace.getText().toString());
                    handled= true;
                }
                return handled;
            }
        });
        searchAdapter = new PlaceSearchAdapter(this, this, placeList);
        sdPlaceList.setAdapter(searchAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_new, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
            case R.id.menu_save:
                checkValidity();
                break;
        }
        return true;
    }

    private void findPlaceUsingKeyword(String keyword) {
        try {
            keyword = URLEncoder.encode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encoding fail!",e);
        }
        Log.e("naver", keyword);
        String apiURL = "https://openapi.naver.com/v1/search/local.json?query="+keyword+"&display=20&start=1&sort=random";    // json 결과
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", "STE1quicYkdzwGviedAg");
        requestHeaders.put("X-Naver-Client-Secret", "Sg0c5tp1JR");

        // progressBar 보이게

        // responseBody 값 받아옴
        new Thread() {
            public void run() {
                HttpURLConnection con = connect(apiURL);
                String res = "";
                try {
                    con.setRequestMethod("GET");
                    for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                        con.setRequestProperty(header.getKey(), header.getValue());
                    }

                    int responseCode = con.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                        res = readBody(con.getInputStream());
                    } else { // 에러 발생
                        res = readBody(con.getErrorStream());
                    }
                } catch (IOException e) {
                    throw new RuntimeException("API 요청과 응답 실패", e);
                } finally {
                    con.disconnect();
                    Message msg = new Message();
                    msg.obj = res;
                    handler.sendMessage(msg);
                }
            }
        }.start();

    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            // after thread work
            refreshAdapter();

            // title이랑 roadAddress만 필요
            Map map = null;
            try {
                map = paramMap(msg.obj);
                ArrayList<Map> items = paramList(map.get("items"));
                placeList.clear();
                for (Map item : items) {
                    SchedulePlace place = new SchedulePlace();
                    place.setPlaceName((String) item.get("title"));
                    place.setPlaceAddress((String) item.get("roadAddress"));
                    placeList.add(place);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            for (Map item : map.get("items")) {
//
//            }
            
            // make progressbar invisible

             return true;
        }
    });


    private static void refreshAdapter() {
        searchAdapter.notifyDataSetChanged();
    }

    private void get(String apiUrl, Map<String, String> requestHeaders){

    }

    private HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body, StandardCharsets.UTF_8);

        try (
                BufferedReader lineReader = new BufferedReader(streamReader)
        ) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

    private void checkValidity() {
        String name = sdName.getText().toString();

        sdName.clearFocus();
        if (name.length() < 1) {
            sdName.setError("1자 이상의 이름을 입력해주세요");
            sdName.requestFocus();
        } else {
            Toast.makeText(this, "내 일정에 추가되었습니다", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(NewScheduleActivity.this, CheckScheduleActivity.class);
            intent.putExtra("scheduleName", name);
            startActivity(intent);
            this.finish();
        }
    }

    @Override
    public void onItemSelected(View view, int position) {
        Log.e("hello", "");
        placeList.clear();
        refreshAdapter();
        TextView tv_name = view.findViewById(R.id.tv_place_name);
        sdPlace.setText(tv_name.getText());
    }

    //파라미터를 map으로 변환 메소드
    public HashMap<String, Object> paramMap(Object object) throws JSONException {
        HashMap<String, Object> hashmap = new HashMap<String, Object>();
        JSONObject json = new JSONObject(String.valueOf(object)); // 받아온 string을 json 으로로 변환
        Iterator i = json.keys(); // json key 요소읽어옴

        while(i.hasNext()){
            String k = i.next().toString(); // key 순차적으로 추출
            hashmap.put(k, json.getString(k)); // key, value를 map에 삽입
        }

        return hashmap;
    }

    public ArrayList<Map> paramList(Object object) throws JSONException {

        ArrayList<Map> arrayList = new ArrayList<Map>();
        JSONArray json = new JSONArray(String.valueOf(object));

        for (int i = 0; i < json.length(); i++) {
            arrayList.add(paramMap(json.getString(i)));
        }
        return arrayList;
    }

    @Override
    public void onTimeSet(int selectedHour, int selectedMinute, int mode) {
        String state = "AM";
        String hour;
        String minute;
        if (selectedHour > 11) {
            selectedHour -= 12;
            state = "PM";
        }
        if (selectedHour == 0) selectedHour = 12;
        hour = String.format("%02d", selectedHour);
        minute = String.format("%02d", selectedMinute);
        timeStartString = hour + minute;
        sdStartTime.setText(hour + ":" + minute + " " + state);
        return;
    }
}
