package com.example.cs496_week4.CheckItems;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_week4.NewItems.FragmentTimeTable;
import com.example.cs496_week4.R;

import java.util.ArrayList;

public class CheckTimeTableActivity extends AppCompatActivity {
    // fields
    private String timeTableName;
    private int timeTableDays;
    private String timeTableStartTime;
    private String timeTableEndTime;
    private ArrayList<String> timeTableDates;
    // _layout elements
    private TextView tt_Name;
    private TextView tt_ChangeBtn;
    private TextView tt_MemberBtn;
    private RecyclerView tt_Members;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checktimetable);


        Intent intent = getIntent();
        timeTableName = intent.getStringExtra("timeTableName");
        timeTableDays = intent.getIntExtra("timeTableDays", 0);
        timeTableStartTime = intent.getStringExtra("timeTableStartTime");
        timeTableEndTime = intent.getStringExtra("timeTableEndTime");

        // set toolbar
        TextView title = findViewById(R.id.toolbar_title);
        title.setText(timeTableName);
        Toolbar toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // set layout elements
        tt_Name = findViewById(R.id.tv_ctt_name);
        tt_ChangeBtn = findViewById(R.id.tv_ctt_btn);
        tt_MemberBtn = findViewById(R.id.tv_ctt_member_btn);
        tt_Members = findViewById(R.id.recycler_view_ctt_members);

        // connect time table name
        tt_Name.setText(timeTableName);
        // connect time table
        FragmentTimeTable frag1 = new FragmentTimeTable(timeTableDays, timeTableStartTime, timeTableEndTime);
        replaceFragment(frag1);
        tt_ChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (true) {
                    replaceFragment(frag1);
                } else {
                    replaceFragment(frag1);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
            case R.id.menu_delete:
                Log.e("Delete", "");
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu) ;

        return true ;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout_ctt, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}
