package com.example.cs496_week4.NewItems;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.applikeysolutions.cosmocalendar.selection.MultipleSelectionManager;
import com.applikeysolutions.cosmocalendar.selection.OnDaySelectedListener;
import com.applikeysolutions.cosmocalendar.view.CalendarView;
import com.example.cs496_week4.R;

import java.util.ArrayList;

public class NewTimeTableActivity extends AppCompatActivity {
    // fields
    private CalendarView calendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtimetable);


        Intent intent = getIntent();

        // set toolbar
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("New Time Table");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_24);
        }

        // connect calendar with its selectionMgr
        calendar = findViewById(R.id.calendarView_ntt);
        calendar.setSelectionManager(new MultipleSelectionManager(new OnDaySelectedListener() {
            @Override
            public void onDaySelected() {
                Log.e(" CALENDAR ", "========== setSelectionManager ==========");
                Log.e(" CALENDAR ", "Selected Dates : " + calendar.getSelectedDates().size());
                Log.e(" CALENDAR ", "Selected Days : " + calendar.getSelectedDays());
            }
        }));

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return true;
    }
}
