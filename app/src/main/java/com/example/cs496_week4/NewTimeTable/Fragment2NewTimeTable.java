package com.example.cs496_week4.NewTimeTable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.cs496_week4.R;

public class Fragment2NewTimeTable extends Fragment {
    // fields

    // Required empty public constructor
    public Fragment2NewTimeTable() {

    }

    public static Fragment2NewTimeTable newInstance() {
        Fragment2NewTimeTable fragment = new Fragment2NewTimeTable();
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
