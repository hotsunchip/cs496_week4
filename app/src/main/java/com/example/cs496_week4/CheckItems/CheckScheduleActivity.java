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

import com.example.cs496_week4.Data.Member;
import com.example.cs496_week4.Data.SchedulePlace;
import com.example.cs496_week4.Main.MainActivity;
import com.example.cs496_week4.NewItems.NewMemberActivity;
import com.example.cs496_week4.NewItems.NewScheduleActivity;
import com.example.cs496_week4.R;

import java.util.ArrayList;

public class CheckScheduleActivity extends AppCompatActivity {
    // fields
    private String scheduleName;
    private String scheduleDate;
    private String scheduleTime;
    private SchedulePlace schedulePlace;
    private ArrayList<Member> scheduleMember;
    // _layout
    private TextView tv_name;
    private TextView tv_date;
    private TextView tv_time;
    private TextView tv_place_name;
    private TextView tv_place_addr;
    private TextView tv_member_btn;
    private TextView tv_member_num;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkschedule);


        Intent intent = getIntent();
        scheduleName = intent.getStringExtra("scheduleName");
        scheduleMember = new ArrayList<>();

        // set toolbar
        TextView title = findViewById(R.id.toolbar_title);
        title.setText(scheduleName);
        Toolbar toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // connect layout elements;
        tv_name = findViewById(R.id.tv_csd_name);
        tv_date = findViewById(R.id.tv_csd_date);
        tv_member_btn = findViewById(R.id.tv_csd_member_btn);
        tv_member_num = findViewById(R.id.tv_csd_member_num);

        tv_name.setText(scheduleName);
        tv_member_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addMemberIntent = new Intent(CheckScheduleActivity.this, NewMemberActivity.class);
                startActivity(addMemberIntent);
            }
        });
        tv_member_num.setText(String.valueOf(scheduleMember.size()));
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

}
