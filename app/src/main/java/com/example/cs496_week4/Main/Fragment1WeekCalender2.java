package com.example.cs496_week4.Main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_week4.R;

import java.util.ArrayList;

public class Fragment1WeekCalender2 extends Fragment {
    // fields
    private RecyclerView time_table;
    private Button scroll_left;
    private Button scroll_right;
    private int height = -1;
    private int width = -1;
    private int num_day = 4;
    private int time_start = 9;
    private int time_end = 15;
    private boolean[][] time_table_state = new boolean[48][100];
    private int num_block;
    private int block_height;
    private int block_width;
    private LinearLayout.LayoutParams block_params;
    private LinearLayout.LayoutParams layout_params;
    private int layout_height;
    private int layout_width;

    // Required empty public constructor
    public Fragment1WeekCalender2() {

    }

    public static Fragment1WeekCalender2 newInstance() {
        Fragment1WeekCalender2 fragment = new Fragment1WeekCalender2();
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
        time_table = view.findViewById(R.id.time_table);
        scroll_left = view.findViewById(R.id.scroll_left);
        scroll_right = view.findViewById(R.id.scroll_right);

        time_table.post(new Runnable() {
            @Override
            public void run() {
                height = time_table.getMeasuredHeight();
                width = time_table.getMeasuredWidth();
            }
        });

        if (height != -1 && width != -1) {
            num_block = time_end - time_start;
            block_height = height / num_block;
            block_width = width / 5;
            block_params = new LinearLayout.LayoutParams(block_width, block_height);

            layout_height = height;
            layout_width = block_width;
            layout_params = new LinearLayout.LayoutParams(layout_width, layout_height);
        } else {
            return view;
        }

        // 리사이클러뷰에 표시할 데이터 리스트 생성.
        ArrayList<LinearLayout> list = new ArrayList<>();
        for (int i = 0; i < num_day; i++) {
            LinearLayout layout = new LinearLayout(getActivity());
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(layout_params);
            layout.setBackgroundColor(Color.RED);

            for (int j = time_start; j < time_end; ++j) {
                LinearLayout block = new LinearLayout(getActivity());
                block.setLayoutParams(block_params);
                block.setBackgroundColor(Color.GREEN);
                layout.addView(block);
            }
            list.add(layout);
        }

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        time_table.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        // 리사이클러Fragment1WeekCalender뷰에 SimpleTextAdapter 객체 지정.
        TimeTableAdapter adapter = new TimeTableAdapter(list);
        time_table.setAdapter(adapter);

        return view;
    }
}
