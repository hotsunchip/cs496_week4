package com.example.cs496_week4.NewItems;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.applikeysolutions.cosmocalendar.selection.MultipleSelectionManager;
import com.applikeysolutions.cosmocalendar.selection.OnDaySelectedListener;
import com.applikeysolutions.cosmocalendar.view.CalendarView;
import com.example.cs496_week4.CheckItems.CheckScheduleActivity;
import com.example.cs496_week4.CheckItems.CheckTimeTableActivity;
import com.example.cs496_week4.R;
import com.example.cs496_week4.TimePickerFragment;

import java.text.DecimalFormat;

public class NewTimeTableActivity extends AppCompatActivity implements TimePickerFragment.OnTimeSetInterface{
    // fields
    private CalendarView ttCalendar;
    private EditText ttName;
    private TextView ttStartTime;
    private TextView ttEndTime;
    private String timeStartString = "0900";
    private String timeEndString = "1600";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtimetable);


        Intent intent = getIntent();

        // set toolbar
        TextView title = findViewById(R.id.toolbar_title);
        title.setText("새 티티 만들기");
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
        ttName = findViewById(R.id.et_ntt_name);
        ttCalendar = findViewById(R.id.calendarView_ntt);
        ttStartTime = findViewById(R.id.tv_ntt_start_time);
        ttEndTime = findViewById(R.id.tv_ntt_end_time);

        // set selectionMgr for calendar
        ttCalendar.setSelectionManager(new MultipleSelectionManager(new OnDaySelectedListener() {
            @Override
            public void onDaySelected() {
                Log.e(" CALENDAR ", "========== setSelectionManager ==========");
                Log.e(" CALENDAR ", "Selected Dates : " + ttCalendar.getSelectedDates().size());
                Log.e(" CALENDAR ", "Selected Days : " + ttCalendar.getSelectedDays());
            }
        }));

        // set clickEventListener for timePicker
        ttStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment mTimePickerFragment = new TimePickerFragment(TimePickerFragment.START_TIME, NewTimeTableActivity.this);
                mTimePickerFragment.show(getSupportFragmentManager(), TimePickerFragment.FRAGMENT_TAG);
            }
        });
        ttEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment mTimePickerFragment = new TimePickerFragment(TimePickerFragment.END_TIME, NewTimeTableActivity.this);
                mTimePickerFragment.show(getSupportFragmentManager(), TimePickerFragment.FRAGMENT_TAG);
            }
        });
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
        String name = ttName.getText().toString();

        ttName.clearFocus();
        if (name.length() < 1) {
            ttName.setError("1자 이상의 이름을 입력해주세요");
            ttName.requestFocus();
        } else if (ttCalendar.getSelectedDates().size() == 0) {
            Toast.makeText(this, "날짜를 선택해주세요", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "내 티티에 저장되었습니다", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(NewTimeTableActivity.this, CheckTimeTableActivity.class);
            intent.putExtra("timeTableName", name);
            intent.putExtra("timeTableStartTime", timeStartString);
            intent.putExtra("timeTableEndTime", timeEndString);
            intent.putExtra("timeTableDays", ttCalendar.getSelectedDays().size());
            startActivity(intent);
            this.finish();
        }
    }

    @Override
    public void onTimeSet(int selectedHour, int selectedMinute, int mode) {
        String hour = String.format("%02d", selectedHour);
        String minute;
        if (selectedMinute == 0) {
            minute = "00";
        } else {
            minute = "30";
        }
        String timeString = hour + minute;
        if (mode == TimePickerFragment.START_TIME) {
            timeStartString = timeString;
            setTimeTextView(ttStartTime, timeString);
        } else {
            timeEndString = timeString;
            setTimeTextView(ttEndTime, timeString);
        }
        return;
    }

    private void setTimeTextView(TextView tv, String time) {
        String state = "AM";
        int hour = Integer.parseInt(time.substring(0, 2));
        if (hour > 11) {
            hour -= 12;
            state = "PM";
        }
        if (hour == 0) hour = 12;
        tv.setText(String.format("%02d", hour) + ":" + time.substring(2) + " " + state);
    }
}
