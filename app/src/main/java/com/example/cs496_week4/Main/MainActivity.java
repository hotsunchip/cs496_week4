package com.example.cs496_week4.Main;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cs496_week4.NewItems.NewScheduleActivity;
import com.example.cs496_week4.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public static MainActivity mContext;
    public static String userToken;
    private static FloatingActionButton mainFab;
    private static FloatingActionButton newScheduleFab;
    private static FloatingActionButton newGroupScheduleFab;
    private static FloatingActionButton newTimeTableFab;
    private static LinearLayout newScheduleFabLayout;
    private static LinearLayout newGroupScheduleFabLayout;
    private static LinearLayout newTimeTableFabLayout;

//    private static TabLayout tabLayout;
    private static ImageButton profileBtn;

    private static Animation rotateOpen;
    private static Animation rotateClose;
    private static Animation fromBottom;
    private static Animation toBottom;


    private TextView calendarMonthYear;
    private DateTimeFormatter monthFormat;
    private String localDate;

    private static boolean mClicked = false;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;


        monthFormat = DateTimeFormatter.ofPattern("yyyy년 MM월").withLocale(Locale.forLanguageTag("ko"));
        localDate = LocalDateTime.now().format(monthFormat);

        calendarMonthYear = findViewById(R.id.tv_cd_YearMonth);
        calendarMonthYear.setText(localDate);

        // fields
        //    public String serverAddress = "192.0.0.0";
        //    EditText dataInput; //서버로 전송할 데이터 입력상자
        //    String str;

        // tab
//        tabLayout = findViewById(R.id.tab_layout);
        FragmentWeekCalendar frag1 = new FragmentWeekCalendar();
//        Fragment2Map frag2 = new Fragment2Map();
//        Fragment3TempTimeTable frag3 = new Fragment3TempTimeTable(3, "1500", "2230");
//        tabLayout.addTab(tabLayout.newTab().setText("시간"),true);
//        tabLayout.addTab(tabLayout.newTab().setText("공간"));
//        tabLayout.addTab(tabLayout.newTab().setText("임시"));
        replaceFragment(frag1);
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                switch (tab.getPosition())
//                {
//                    case 0 :
//                        if (mClicked) closeFab();
//                        replaceFragment(frag1);
//                        break;
//                    case 1 :
//                        if (mClicked) closeFab();
//                        replaceFragment(frag2);
//                        break;
//                    case 2 :
//                        if (mClicked) closeFab();
//                        replaceFragment(frag3);
//                        break;
//                }
//            }
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//            }
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//            }
//        });
//        tabLayout.getTabAt(0).select();


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
                intent.putExtra("isGroup", false);
                startActivity(intent);
            }
        });
        newGroupScheduleFab = findViewById(R.id.newGroupScheduleFab);
        newGroupScheduleFabLayout = findViewById(R.id.newGroupScheduleFabLayout);
        newGroupScheduleFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFab();
                Intent intent = new Intent(MainActivity.this, CodeActivity.class);
                intent.putExtra("isTT", false);
                startActivity(intent);
            }
        });
        newTimeTableFab = findViewById(R.id.newTimeTableFab);
        newTimeTableFabLayout = findViewById(R.id.newTimeTableFabLayout);
        newTimeTableFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFab();
                Intent intent = new Intent(MainActivity.this, CodeActivity.class);
                intent.putExtra("isTT", true);
                startActivity(intent);
            }
        });

        rotateOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_open_menu_anim);
        rotateClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_close_menu_anim);
        fromBottom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.to_bottom_anim);


        // drawer
        profileBtn = findViewById(R.id.main_profile) ;
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

        //CallRetrofit callRetrofit = new CallRetrofit();
        //callRetrofit.userNameExists("woojin");
        //callRetrofit.userEmailExists("wjl0209@kaist.ac.kr");
        //callRetrofit.signUp(new Input__signUp("qwe", "qwe@kaist.ac.kr", "1111"));
        //String token = callRetrofit.signIn(new Input__signIn("qwe@kaist.ac.kr", "1111")).getToken();
        //Log.d("token", token);
        //Log.d("resetpwd", Boolean.toString(callRetrofit.resetPassword(token, "666")));
        //Log.d("wtmCreate", callRetrofit.wtmCreate(token, new Input__wtmCreate("test000", new String[]{"2022-01-01", "2022-01-02", "2022-01-03"}, "0600", "1300", null)).toString());
        /*int apptId = callRetrofit.apptCreate(token, new Input__apptCreate("appt1", "2022-01-02T08:00", new float[]{-79.3968307f, 43.6656976f})).getApptIdentifier();
        Log.d("accept", callRetrofit.apptAccept(token, apptId).getError());
        Log.d("reject", callRetrofit.apptReject(token, apptId).getError());
        Log.d("apptInfo", callRetrofit.apptInfo(token, apptId).getOwner());
        Log.d("delete", callRetrofit.apptDelete(token, apptId).getResult());*/
        //Log.d("apptInvite", callRetrofit.apptInvite(token, new Input__apptInvite(apptId, new String[]{"bb"})).toString());
        //int wtmId = callRetrofit.wtmCreate(token, new Input__wtmCreate("test000", new String[]{"2022-01-01", "2022-01-02", "2022-01-03"}, "0600", "1300", new String[]{})).getWtmId();
        //Log.d("wtmRespond", callRetrofit.wtmRespond(token, new Input__wtmRespond(933, new wtmRespond_times[]{new wtmRespond_times("2022-01-02", new String[]{"0800", "0900", "1000"})})).getWtm()[0].get_id());
        //Log.d("setAlarm", "setAlarm: " + callRetrofit.setAlarm(token, new Input__setAlarm(new double[]{42.448139, -71.260108, 42.3793386, -71.0676069}, "dJwXjQv8S62Il2g-hBUT67:APA91bGI521s_mxPO0ZVzUwh4nPYqOQ10B77Viw6jnpA-2pAo8fvauXPCI8D3ejPWrnlgIiDOM44diubrQIfJkEMs8rxBlJrIbfAoXCCzy6WVxXxupxt0lGDn8uld_M9j8twZE52SVbV", "2022-01-25T17:00")).getTime());

        // Get Firebase Device Token
        /*FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        //String msg = getString(R.string.msg_token_fmt, token);
                        Log.d("token", token);
                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });*/

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
        newScheduleFab.setVisibility(View.VISIBLE);
        newGroupScheduleFab.setVisibility(View.VISIBLE);
        newTimeTableFab.setVisibility(View.VISIBLE);
        // set animation
        newScheduleFabLayout.startAnimation(fromBottom);
        newGroupScheduleFabLayout.startAnimation(fromBottom);
        newTimeTableFabLayout.startAnimation(fromBottom);
        mainFab.startAnimation(rotateOpen);
        mClicked = !mClicked;
    }
    private void closeFab() {
        // set animation
        newScheduleFabLayout.startAnimation(toBottom);
        newGroupScheduleFabLayout.startAnimation(toBottom);
        newTimeTableFabLayout.startAnimation(toBottom);
        mainFab.startAnimation(rotateClose);
        mClicked = !mClicked;
        // set visibility
        newScheduleFabLayout.setVisibility(View.GONE);
        newGroupScheduleFabLayout.setVisibility(View.GONE);
        newTimeTableFabLayout.setVisibility(View.GONE);
        newScheduleFab.setVisibility(View.GONE);
        newGroupScheduleFab.setVisibility(View.GONE);
        newTimeTableFab.setVisibility(View.GONE);
    }
}