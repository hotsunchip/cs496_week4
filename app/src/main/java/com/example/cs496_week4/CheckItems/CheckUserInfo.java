package com.example.cs496_week4.CheckItems;

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
import com.example.cs496_week4.Data.SchedulePlace;
import com.example.cs496_week4.Main.MainActivity;
import com.example.cs496_week4.NewItems.NewScheduleActivity;
import com.example.cs496_week4.R;
import com.example.cs496_week4.Retrofit.CallRetrofit;
import com.example.cs496_week4.Retrofit.Data.appt.Input__apptCreate;
import com.example.cs496_week4.Retrofit.Data.appt.Output__apptCreate;

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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CheckUserInfo extends AppCompatActivity implements PlaceSearchAdapter.OnListItemSelectedInterface {
    // fields
    private EditText et_userPlace;
    private RecyclerView recyclerView_placeSearch;
    private static PlaceSearchAdapter searchAdapter;
    private ArrayList<SchedulePlace> placeList;
    private boolean isNewComer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkuserinfo);


        Intent intent = getIntent();
        isNewComer = intent.getBooleanExtra("isNewComer", false);
        placeList = new ArrayList<>();

        // set toolbar
        TextView title = findViewById(R.id.toolbar_title);

        Toolbar toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (isNewComer) {
            title.setText("??? ?????? ????????????");
            if (actionBar != null) {
                actionBar.setDisplayShowTitleEnabled(false);
                actionBar.setDisplayHomeAsUpEnabled(false);
            }
        } else {
            title.setText("??? ?????? ????????????");
            if (actionBar != null) {
                actionBar.setDisplayShowTitleEnabled(false);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }

        et_userPlace = findViewById(R.id.et_user_place);
        recyclerView_placeSearch = findViewById(R.id.recycler_view_place);
        if (!isNewComer) et_userPlace.setText(MainActivity.userPlace);
        et_userPlace.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    findPlaceUsingKeyword(et_userPlace.getText().toString());
                    handled = true;
                }
                return handled;
            }
        });
        searchAdapter = new PlaceSearchAdapter(this, this, placeList);
        recyclerView_placeSearch.setAdapter(searchAdapter);

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

    private void checkValidity() {
        String place = et_userPlace.getText().toString();

        et_userPlace.clearFocus();
        if (place.length() < 1) {
            et_userPlace.setError("??? ?????? ????????? ??????????????????");
            et_userPlace.requestFocus();
        } else {
            CallRetrofit callRetrofit = new CallRetrofit();
            String token = MainActivity.userToken;

            if (isNewComer) {
                Toast.makeText(this, "??? ????????? ?????????????????????", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "??? ????????? ?????????????????????", Toast.LENGTH_LONG).show();
            }
            MainActivity.userPlace = place;
            this.finish();
        }
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

    private static void refreshAdapter() {
        searchAdapter.notifyDataSetChanged();
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
                    String name = (String) item.get("title");
                    place.setPlaceName( name.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
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

    @Override
    public void onItemSelected(View view, int position) {
        placeList.clear();
        refreshAdapter();
        TextView tv_name = view.findViewById(R.id.tv_place_name);
        et_userPlace.setText(tv_name.getText());
    }
}