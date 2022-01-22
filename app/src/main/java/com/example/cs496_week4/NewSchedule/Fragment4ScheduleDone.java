package com.example.cs496_week4.NewSchedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.applikeysolutions.cosmocalendar.view.CalendarView;
import com.example.cs496_week4.NewTimeTable.Fragment3TimeTableDone;
import com.example.cs496_week4.R;

public class Fragment4ScheduleDone extends Fragment {
    // fields

    // Required empty public constructor
    public Fragment4ScheduleDone() {

    }

    public static Fragment4ScheduleDone newInstance() {
        Fragment4ScheduleDone fragment = new Fragment4ScheduleDone();
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
        View view = inflater.inflate(R.layout.fragment_new_tt_3, container, false);

        return view;
    }
}
