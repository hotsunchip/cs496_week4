package com.example.cs496_week4.Main;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_week4.R;

import java.util.ArrayList;

public class Fragment1WeekCalender extends Fragment {
    // fields

    // Required empty public constructor
    public Fragment1WeekCalender() {

    }

    public static Fragment1WeekCalender newInstance() {
        Fragment1WeekCalender fragment = new Fragment1WeekCalender();
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
