package com.example.cs496_week4.NewSchedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.cs496_week4.Main.FragmentExample;
import com.example.cs496_week4.R;

public class Fragment2ScheduleDate extends Fragment {
    // fields
    // Required empty public constructor
    public Fragment2ScheduleDate() {

    }

    public static Fragment2ScheduleDate newInstance() {
        Fragment2ScheduleDate fragment = new Fragment2ScheduleDate();
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
        View view = inflater.inflate(R.layout.fragment_main_1, container, false);

        return view;
    }
}