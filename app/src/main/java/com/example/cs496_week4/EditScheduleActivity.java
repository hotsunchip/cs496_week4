package com.example.cs496_week4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_week4.AdapterListener.PlaceSearchAdapter;
import com.example.cs496_week4.CheckItems.CheckScheduleActivity;
import com.example.cs496_week4.Data.SchedulePlace;
import com.example.cs496_week4.Main.MainActivity;
import com.example.cs496_week4.NewItems.NewScheduleActivity;
import com.example.cs496_week4.R;
import com.example.cs496_week4.Retrofit.CallRetrofit;
import com.example.cs496_week4.Retrofit.Data.appt.Input__apptCreate;
import com.example.cs496_week4.Retrofit.Data.appt.Model__apptInfo;
import com.example.cs496_week4.Retrofit.Data.appt.Output__apptCreate;
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

public class EditScheduleActivity extends AppCompatActivity implements PlaceSearchAdapter.OnListItemSelectedInterface, TimePickerFragment.OnTimeSetInterface {
    // fields
    private EditText sdName;
    private TextView sdStartDate;
    private TextView sdStartTime;
    private EditText sdPlace;
    private RecyclerView sdPlaceList;
    private static PlaceSearchAdapter searchAdapter;
    private ArrayList<SchedulePlace> placeList;
    public String responseBody;
    private int apptId;
    private String timeStartString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newschedule);


        Intent intent = getIntent();
        apptId = intent.getIntExtra("apptId", -1);
        Model__apptInfo apptInfo = new CallRetrofit().apptInfo(MainActivity.userToken, apptId);
        String date = apptInfo.getStartTime();
        int hour = Integer.parseInt(date.substring(11, 13));
        String strHour;
        String state = "AM";
        if (hour > 11) {
            hour -= 12;
            state = "PM";
        }
        if (hour == 0 && state.equals("PM")) hour = 12;
        strHour = String.format("%02d", hour);

        placeList = new ArrayList<>();

        // set toolbar
        TextView title = findViewById(R.id.toolbar_title);
        title.setText("?????? ????????????");
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

        sdName.setText(apptInfo.getName());
        sdPlace.setText(apptInfo.getDestination());
        sdStartTime.setText(strHour + ":" + date.substring(14, 16) + state);

        // add additional works
        Calendar mcurrentTime = Calendar.getInstance();
        sdStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment mTimePickerFragment = new TimePickerFragment(TimePickerFragment.CURRENT_TIME, EditScheduleActivity.this);
                mTimePickerFragment.show(getSupportFragmentManager(), TimePickerFragment.FRAGMENT_TAG);
            }
        });
        sdPlace.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    findPlaceUsingKeyword(sdPlace.getText().toString());
                    handled = true;
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
            throw new RuntimeException("encoding fail!", e);
        }
        Log.e("naver", keyword);
        String apiURL = "https://openapi.naver.com/v1/search/local.json?query=" + keyword + "&display=20&start=1&sort=random";    // json ??????
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml ??????

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", "STE1quicYkdzwGviedAg");
        requestHeaders.put("X-Naver-Client-Secret", "Sg0c5tp1JR");

        // progressBar ?????????

        // responseBody ??? ?????????
        new Thread() {
            public void run() {
                HttpURLConnection con = connect(apiURL);
                String res = "";
                try {
                    con.setRequestMethod("GET");
                    for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                        con.setRequestProperty(header.getKey(), header.getValue());
                    }

                    int responseCode = con.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) { // ?????? ??????
                        res = readBody(con.getInputStream());
                    } else { // ?????? ??????
                        res = readBody(con.getErrorStream());
                    }
                } catch (IOException e) {
                    throw new RuntimeException("API ????????? ?????? ??????", e);
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

            // title?????? roadAddress??? ??????
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

    private void get(String apiUrl, Map<String, String> requestHeaders) {

    }

    private HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL??? ?????????????????????. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("????????? ??????????????????. : " + apiUrl, e);
        }
    }

    private String readBody(InputStream body) {
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
            throw new RuntimeException("API ????????? ????????? ??????????????????.", e);
        }
    }

    private void checkValidity() {
        String name = sdName.getText().toString();

        sdName.clearFocus();
        if (name.length() < 1) {
            sdName.setError("1??? ????????? ????????? ??????????????????");
            sdName.requestFocus();
        } else {
            CallRetrofit callRetrofit = new CallRetrofit();
            String token = MainActivity.userToken;
            String apptName = sdName.getText().toString();
            String apptDate = sdStartDate.getText().toString()
                    .replace("??? ", "-")
                    .replace("??? ", "-")
                    .replace("???", "");
            String[] splitted = apptDate.split("-");
            String year = splitted[0];
            String month = splitted[1];
            String day = splitted[2];
            if (month.length() < 2) {
                month = "0" + month;
            }
            if (day.length() < 2) {
                day = "0" + day;
            }
            apptDate = year + "-" + month + "-" + day + "T";

            String apptTime = sdStartTime.getText().toString();
            String[] timeSplitted = apptTime.split(":");
            String hour = timeSplitted[0];
            String minute = timeSplitted[1];
            if(minute.substring(minute.length()-2, minute.length()).equals("PM") && Integer.parseInt(hour) != 12)
                hour = Integer.toString(Integer.parseInt(hour)+12);
            minute = minute
                    .replace(" AM", "")
                    .replace(" PM", "");
            if (hour.length() < 2) {
                hour = "0" + hour;
            }
            if (minute.length() < 2) {
                minute = "0" + minute;
            }
            apptTime = hour + ":" + minute;

            String apptStartTime = apptDate + apptTime;
            String apptDest = sdPlace.getText().toString();

            Input__apptCreate apptInfo = new Input__apptCreate(apptName, apptStartTime, apptDest);

            callRetrofit.updateAppt(MainActivity.userToken, apptId, apptInfo);

            Toast.makeText(this, "????????? ?????????????????????", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(EditScheduleActivity.this, CheckScheduleActivity.class);
            intent.putExtra("apptId", apptId);
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

    //??????????????? map?????? ?????? ?????????
    public HashMap<String, Object> paramMap(Object object) throws JSONException {
        HashMap<String, Object> hashmap = new HashMap<String, Object>();
        JSONObject json = new JSONObject(String.valueOf(object)); // ????????? string??? json ????????? ??????
        Iterator i = json.keys(); // json key ???????????????

        while (i.hasNext()) {
            String k = i.next().toString(); // key ??????????????? ??????
            hashmap.put(k, json.getString(k)); // key, value??? map??? ??????
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
        if (selectedHour == 0 && state.equals("PM")) selectedHour = 12;
        hour = String.format("%02d", selectedHour);
        minute = String.format("%02d", selectedMinute);
        hour = Integer.toString(Integer.parseInt(hour));
        timeStartString = hour + minute;
        sdStartTime.setText(hour + ":" + minute + " " + state);
        return;
    }
}
