package com.example.cs496_week4.NewTimeTable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.applikeysolutions.cosmocalendar.view.CalendarView;
import com.example.cs496_week4.R;

public class Fragment3TimeTableDone extends Fragment {
    // fields
    private CalendarView calendar;

    // Required empty public constructor
    public Fragment3TimeTableDone() {

    }

    public static Fragment3TimeTableDone newInstance() {
        Fragment3TimeTableDone fragment = new Fragment3TimeTableDone();
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
