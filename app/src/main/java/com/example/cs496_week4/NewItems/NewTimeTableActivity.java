package com.example.cs496_week4.NewItems;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.applikeysolutions.cosmocalendar.selection.MultipleSelectionManager;
import com.applikeysolutions.cosmocalendar.selection.OnDaySelectedListener;
import com.applikeysolutions.cosmocalendar.view.CalendarView;
import com.example.cs496_week4.R;
import com.example.cs496_week4.TimePickerFragment;

public class NewTimeTableActivity extends AppCompatActivity {
    // fields
    private CalendarView ttCalendar;
    private EditText ttName;
    private TextView ttStartTime;
    private TextView ttEndTime;

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
                TimePickerFragment mTimePickerFragment = new TimePickerFragment(TimePickerFragment.START_TIME);
                mTimePickerFragment.show(getSupportFragmentManager(), TimePickerFragment.FRAGMENT_TAG);
            }
        });
        ttEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment mTimePickerFragment = new TimePickerFragment(TimePickerFragment.END_TIME);
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
            this.finish();
        }
    }
}
