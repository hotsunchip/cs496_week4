package com.example.cs496_week4.Main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.cs496_week4.NewMeetingPlace.NewMeetingPlaceActivity;
import com.example.cs496_week4.NewSchedule.NewScheduleActivity;
import com.example.cs496_week4.NewTimeTable.NewTimeTableActivity;
import com.example.cs496_week4.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private static FloatingActionButton mainFab;
    private static FloatingActionButton newScheduleFab;
    private static FloatingActionButton newGroupScheduleFab;
    private static FloatingActionButton newTimeTableFab;
    private static LinearLayout mainFabLayout;
    private static LinearLayout newScheduleFabLayout;
    private static LinearLayout newGroupScheduleFabLayout;
    private static LinearLayout newTimeTableFabLayout;
    ImageButton profileBtn;

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
        newScheduleFabLayout = findViewById(R.id.newScheduleFabLayout);
        newScheduleFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewScheduleActivity.class);
                startActivity(intent);
            }
        });
        newGroupScheduleFab = findViewById(R.id.newGroupScheduleFab);
        newGroupScheduleFabLayout = findViewById(R.id.newGroupScheduleFabLayout);
        newGroupScheduleFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewTimeTableActivity.class);
                startActivity(intent);
            }
        });
        newTimeTableFab = findViewById(R.id.newTimeTableFab);
        newTimeTableFabLayout = findViewById(R.id.newTimeTableFabLayout);
        newTimeTableFab.setOnClickListener(new View.OnClickListener() {
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


        // drawer
        profileBtn = findViewById(R.id.main_profile) ;
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("helloworld", "");
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.mainDrawer) ;
                if (!drawer.isDrawerOpen(Gravity.RIGHT)) {
                    drawer.openDrawer(Gravity.RIGHT);
                }
            }
        });

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
            newScheduleFabLayout.setVisibility(View.VISIBLE);
            newGroupScheduleFabLayout.setVisibility(View.VISIBLE);
            newTimeTableFabLayout.setVisibility(View.VISIBLE);
            // set animation
            newScheduleFabLayout.startAnimation(fromBottom);
            newGroupScheduleFabLayout.startAnimation(fromBottom);
            newTimeTableFabLayout.startAnimation(fromBottom);
            mainFab.startAnimation(rotateOpen);

        } else {
            // set visibility
            newScheduleFabLayout.setVisibility(View.GONE);
            newGroupScheduleFabLayout.setVisibility(View.GONE);
            newTimeTableFabLayout.setVisibility(View.GONE);
            // set animation
            newScheduleFabLayout.startAnimation(toBottom);
            newGroupScheduleFabLayout.startAnimation(toBottom);
            newTimeTableFabLayout.startAnimation(toBottom);
            mainFab.startAnimation(rotateClose);

        }
    }
}