package com.example.cs496_week4.NewTimeTable;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.applikeysolutions.cosmocalendar.selection.MultipleSelectionManager;
import com.applikeysolutions.cosmocalendar.selection.OnDaySelectedListener;
import com.applikeysolutions.cosmocalendar.view.CalendarView;
import com.example.cs496_week4.R;

public class Fragment2TimeTableDate extends Fragment {
    // fields
    private CalendarView calendar;

    // Required empty public constructor
    public Fragment2TimeTableDate() {

    }

    public static Fragment2TimeTableDate newInstance() {
        Fragment2TimeTableDate fragment = new Fragment2TimeTableDate();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 변수 초기화
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_tt_2, container, false);
        calendar = view.findViewById(R.id.calendarView_ntt);
        calendar.setSelectionManager(new MultipleSelectionManager(new OnDaySelectedListener() {
            @Override
            public void onDaySelected() {
                Log.e(" CALENDAR ", "========== setSelectionManager ==========");
                Log.e(" CALENDAR ", "Selected Dates : " + calendar.getSelectedDates().size());
                Log.e(" CALENDAR ", "Selected Days : " + calendar.getSelectedDays());
            }
        }));

        return view;
    }
}
