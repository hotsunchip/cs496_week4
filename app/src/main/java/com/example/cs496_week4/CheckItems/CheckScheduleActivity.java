package com.example.cs496_week4.CheckItems;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cs496_week4.Data.Member;
import com.example.cs496_week4.Data.SchedulePlace;
import com.example.cs496_week4.EditScheduleActivity;
import com.example.cs496_week4.Main.CodeActivity;
import com.example.cs496_week4.Main.MainActivity;
import com.example.cs496_week4.NewItems.NewMemberActivity;
import com.example.cs496_week4.R;
import com.example.cs496_week4.Retrofit.CallRetrofit;
import com.example.cs496_week4.Retrofit.Data.appt.Model__apptInfo;

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
    private int apptId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkschedule);


        Intent intent = getIntent();
        scheduleName = intent.getStringExtra("scheduleName");
        scheduleMember = new ArrayList<>();


        apptId = intent.getIntExtra("apptId", -1);
        Model__apptInfo apptInfo = new CallRetrofit().apptInfo(MainActivity.userToken, apptId);
        String date = apptInfo.getStartTime();
        String time = Integer.toString(Integer.parseInt(date.substring(11, 13)));

        // set toolbar
        TextView title = findViewById(R.id.toolbar_title);
        title.setText(apptInfo.getName());
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


        TextView csd_name = findViewById(R.id.tv_csd_name);
        csd_name.setText(apptInfo.getName() + " ");
        TextView csd_date = findViewById(R.id.tv_csd_date);
        csd_date.setText(date.substring(0, 4) + "년 " + date.substring(5, 7) + "월 " + date.substring(8, 10) + "일 ");
        TextView schedule_time = findViewById(R.id.tv_schedule_time);
        schedule_time.setText(time + ":" + date.substring(14, 16));
        TextView schedule_place_name = findViewById(R.id.tv_schedule_place_name);
        schedule_place_name.setText(apptInfo.getDestination());
        TextView schedule_place_addr = findViewById(R.id.tv_schedule_place_addr);
        schedule_place_addr.setText("");

        Button edit_info = findViewById(R.id.edit_appt_info);
        edit_info.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckScheduleActivity.this, EditScheduleActivity.class);
                intent.putExtra("apptId", apptId);
                startActivity(intent);
            }
        });

        Button check_dest = findViewById(R.id.check_dest_position);
        check_dest.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckScheduleActivity.this, CheckDestPosition.class);
                intent.putExtra("apptId", apptId);
                startActivity(intent);
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
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }
}
