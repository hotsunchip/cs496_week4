package com.example.cs496_week4.NewTimeTable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.cs496_week4.Main.Fragment1WeekCalender;
import com.example.cs496_week4.R;

public class Fragment1TimeTableCode extends Fragment {
    // fields
    private TextView tv_new_timetable;
    // Required empty public constructor
    public Fragment1TimeTableCode() {

    }

    public static Fragment1TimeTableCode newInstance() {
        Fragment1TimeTableCode fragment = new Fragment1TimeTableCode();
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
        tv_new_timetable = view.findViewById(R.id.tv_new_timetable);
        tv_new_timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }
}
