package com.example.cs496_week4.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    public static MainActivity mContext;
    private static FloatingActionButton mainFab;
    private static FloatingActionButton newScheduleFab;
    private static FloatingActionButton newGroupScheduleFab;
    private static FloatingActionButton newTimeTableFab;
    private static LinearLayout newScheduleFabLayout;
    private static LinearLayout newGroupScheduleFabLayout;
    private static LinearLayout newTimeTableFabLayout;

    private static TabLayout tabLayout;
    private static ImageButton profileBtn;

    private static Animation rotateOpen;
    private static Animation rotateClose;
    private static Animation fromBottom;
    private static Animation toBottom;

    private static boolean mClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        // fields
        //    public String serverAddress = "192.0.0.0";
        //    EditText dataInput; //서버로 전송할 데이터 입력상자
        //    String str;

        // set main toolbar
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        // tab
        tabLayout = findViewById(R.id.tab_layout);
        Fragment1WeekCalender frag1 = new Fragment1WeekCalender();
        Fragment2Map frag2 = new Fragment2Map();
        tabLayout.addTab(tabLayout.newTab().setText("시간"),true);
        tabLayout.addTab(tabLayout.newTab().setText("공간"));
        replaceFragment(frag1);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition())
                {
                    case 0 :
                        if (mClicked) closeFab();
                        replaceFragment(frag1);
                        break;
                    case 1 :
                        if (mClicked) closeFab();
                        replaceFragment(frag2);
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        tabLayout.getTabAt(0).select();


        // fab
        mainFab = findViewById(R.id.mainFab);
        mainFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFabClicked();
            }
        });

        // variables for fab
        newScheduleFab = findViewById(R.id.newScheduleFab);
        newScheduleFabLayout = findViewById(R.id.newScheduleFabLayout);
        newScheduleFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFab();
                Intent intent = new Intent(MainActivity.this, NewScheduleActivity.class);
                startActivity(intent);
            }
        });
        newGroupScheduleFab = findViewById(R.id.newGroupScheduleFab);
        newGroupScheduleFabLayout = findViewById(R.id.newGroupScheduleFabLayout);
        newGroupScheduleFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFab();
                Intent intent = new Intent(MainActivity.this, NewTimeTableActivity.class);
                startActivity(intent);
            }
        });
        newTimeTableFab = findViewById(R.id.newTimeTableFab);
        newTimeTableFabLayout = findViewById(R.id.newTimeTableFabLayout);
        newTimeTableFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFab();
                Intent intent = new Intent(MainActivity.this, NewMeetingPlaceActivity.class);
                startActivity(intent);
            }
        });

        rotateOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_open_menu_anim);
        rotateClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_close_menu_anim);
        fromBottom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.to_bottom_anim);


        // drawer
//        profileBtn = findViewById(R.id.main_profile) ;
//        profileBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("helloworld", "");
//                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.mainDrawer) ;
//                if (!drawer.isDrawerOpen(Gravity.RIGHT)) {
//                    drawer.openDrawer(Gravity.RIGHT);
//                }
//            }
//        });

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

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout_main, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    private void onFabClicked() {
        if (!mClicked) {
            openFab();
        } else {
            closeFab();
        }
    }
    private void openFab() {
        // set visibility
        newScheduleFabLayout.setVisibility(View.VISIBLE);
        newGroupScheduleFabLayout.setVisibility(View.VISIBLE);
        newTimeTableFabLayout.setVisibility(View.VISIBLE);
        // set animation
        newScheduleFabLayout.startAnimation(fromBottom);
        newGroupScheduleFabLayout.startAnimation(fromBottom);
        newTimeTableFabLayout.startAnimation(fromBottom);
        mainFab.startAnimation(rotateOpen);
        mClicked = !mClicked;
    }
    private void closeFab() {
        // set visibility
        newScheduleFabLayout.setVisibility(View.GONE);
        newGroupScheduleFabLayout.setVisibility(View.GONE);
        newTimeTableFabLayout.setVisibility(View.GONE);
        // set animation
        newScheduleFabLayout.startAnimation(toBottom);
        newGroupScheduleFabLayout.startAnimation(toBottom);
        newTimeTableFabLayout.startAnimation(toBottom);
        mainFab.startAnimation(rotateClose);
        mClicked = !mClicked;
    }
}