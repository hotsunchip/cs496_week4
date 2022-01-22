package com.example.cs496_week4.NewTimeTable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.applikeysolutions.cosmocalendar.view.CalendarView;
import com.example.cs496_week4.R;

public class Fragment1TimeTableName extends Fragment {
    // fields
    private CalendarView calendar;

    // Required empty public constructor
    public Fragment1TimeTableName() {

    }

    public static Fragment1TimeTableName newInstance() {
        Fragment1TimeTableName fragment = new Fragment1TimeTableName();
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
        View view = inflater.inflate(R.layout.fragment_new_tt_1, container, false);

        return view;
    }
}
