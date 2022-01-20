package com.example.cs496_week4.Main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.cs496_week4.NewMeetingPlace.NewMeetingPlaceActivity;
import com.example.cs496_week4.NewSchedule.NewScheduleActivity;
import com.example.cs496_week4.NewTimeTable.NewTimeTableActivity;
import com.example.cs496_week4.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private static FloatingActionButton mainFab;
    private static FloatingActionButton newScheduleFab;
    private static FloatingActionButton newTimeTableFab;
    private static FloatingActionButton newMeetingPlaceFab;

    private static Animation rotateOpen;
    private static Animation rotateClose;
    private static Animation fromBottom;
    private static Animation toBottom;

    private static boolean mClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // fields
        //    public String serverAddress = "192.0.0.0";
        //    EditText dataInput; //서버로 전송할 데이터 입력상자
        //    String str;

        // set main toolbar
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(false);


        mainFab = findViewById(R.id.mainFab);
        mainFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClicked = !mClicked;
                onFabClicked();
            }
        });

        // variables for fab
        newScheduleFab = findViewById(R.id.newScheduleFab);
        newScheduleFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewScheduleActivity.class);
                startActivity(intent);
            }
        });
        newTimeTableFab = findViewById(R.id.newTimeTableFab);
        newTimeTableFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewTimeTableActivity.class);
                startActivity(intent);
            }
        });
        newMeetingPlaceFab = findViewById(R.id.newMeetingPlaceFab);
        newMeetingPlaceFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewMeetingPlaceActivity.class);
                startActivity(intent);
            }
        });

        rotateOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_open_menu_anim);
        rotateClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_close_menu_anim);
        fromBottom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.to_bottom_anim);

        // test for socket
        /* dataInput = findViewById(R.id.dataInput);
        Button socketConnectBtn = findViewById(R.id.socketConnectBtn);

        socketConnectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = dataInput.getText().toString();
                SocketThread thread = new SocketThread(MainActivity.this, serverAddress, str);
                thread.start();
            }
        }); */
    }

    private void onFabClicked() {
        if (mClicked) {
            // set visibility
            newScheduleFab.setVisibility(View.VISIBLE);
            newTimeTableFab.setVisibility(View.VISIBLE);
            newMeetingPlaceFab.setVisibility(View.VISIBLE);
            // set animation
            newScheduleFab.startAnimation(fromBottom);
            newTimeTableFab.startAnimation(fromBottom);
            newMeetingPlaceFab.startAnimation(fromBottom);
            mainFab.startAnimation(rotateOpen);

        } else {
            // set visibility
            newScheduleFab.setVisibility(View.INVISIBLE);
            newTimeTableFab.setVisibility(View.INVISIBLE);
            newMeetingPlaceFab.setVisibility(View.INVISIBLE);
            // set animation
            newScheduleFab.startAnimation(toBottom);
            newTimeTableFab.startAnimation(toBottom);
            newMeetingPlaceFab.startAnimation(toBottom);
            mainFab.startAnimation(rotateClose);

        }
    }
}