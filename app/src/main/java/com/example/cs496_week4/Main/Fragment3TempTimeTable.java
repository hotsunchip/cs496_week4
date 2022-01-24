package com.example.cs496_week4.Main;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_week4.R;

import java.util.ArrayList;

import com.example.cs496_week4.AdapterListener.DragSelectionItemTouchListener;
import com.example.cs496_week4.AdapterListener.LongPressItemTouchListener;
import com.example.cs496_week4.AdapterListener.TimeTableAdapter;

public class Fragment3TempTimeTable extends Fragment implements TimeTableAdapter.OnListItemSelectedInterface{
    // fields
    private RecyclerView time_table;
    private int height = -1;
    private int width = -1;
    private int num_day = 7;
    private int time_start = 0;
    private int time_end = 5;
    private boolean[][] time_table_state = new boolean[48][100];
    private int num_block = (time_end - time_start)*2; // if (time_end < time_start) time_end += 24
    private int block_height = 100;
    private int block_width = 160;
    private LinearLayout.LayoutParams layout_params;
    private int layout_height;
    private int layout_width;
    ArrayList<View> mTimeBtns;
    ArrayList<Integer> mTimesAvailable;
    ArrayList<Integer> mTimesSample;
    private boolean blockSelected;

    // Required empty public constructor
    public Fragment3TempTimeTable() {

    }

    public static Fragment3TempTimeTable newInstance() {
        Fragment3TempTimeTable fragment = new Fragment3TempTimeTable();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 변수 초기화
        mTimesSample = new ArrayList<Integer>();
        mTimesSample.add(5);
        mTimesSample.add(15);
        mTimesSample.add(35);
        mTimesSample.add(75);
        mTimesSample.add(95);
        mTimesSample.add(115);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_3, container, false);
        time_table = view.findViewById(R.id.recycler_view_table);
//        scroll_left = view.findViewById(R.id.scroll_left);
//        scroll_right = view.findViewById(R.id.scroll_right);

        mTimesAvailable = new ArrayList<Integer>();
//        mTimeBtns = new ArrayList<View>();
//        for (int k = 0; k < num_block * num_day; k ++) mTimeBtns.add(new View(getContext()));
        time_table.post(new Runnable() {
            @Override
            public void run() {
                height = time_table.getMeasuredHeight();
                width = time_table.getMeasuredWidth();
            }
        });

        if (height != -1 && width != -1) {
            layout_height = block_height;
            layout_width = block_width;
            layout_params = new LinearLayout.LayoutParams(layout_width, layout_height);
        } else {
            layout_params = new LinearLayout.LayoutParams(block_width, block_height);
        }

        // 리사이클러뷰에 GridLayoutManager 객체 지정.
        time_table.setLayoutManager(new GridLayoutManager(getActivity(), num_day, GridLayoutManager.VERTICAL, false));

        // 리사이클러Fragment1WeekCalender뷰에 SimpleTextAdapter 객체 지정.
        TimeTableAdapter adapter = new TimeTableAdapter(getContext(), this::onItemSelected, num_block * num_day, mTimesSample);
        time_table.setAdapter(adapter);

        time_table.addOnItemTouchListener(new DragSelectionItemTouchListener(getContext(), new LongPressItemTouchListener.OnItemInteractionListener() {

            @Override
            public void onLongItemClicked(RecyclerView recyclerView, TimeTableAdapter.ViewHolder mViewHolderTouched, int position) {
                Log.e("ItemTouchListener", "onLongItemClicked");
                Integer pos = position;
                if (mViewHolderTouched.selected) {
                    blockSelected = false;
                    mViewHolderTouched.btn.setBackgroundColor(Color.parseColor("#FFDFC7AF"));
                    mViewHolderTouched.selected = blockSelected;
                    if (mTimesAvailable.contains(position)) {
                        mTimesAvailable.remove(pos);
                    }
                    Log.e("REMOVED", mTimesAvailable.toString());
                } else {
                    blockSelected = true;
                    mViewHolderTouched.btn.setBackgroundColor(Color.RED);
                    mViewHolderTouched.selected = blockSelected;
                    mTimesAvailable.add(pos);
                    Log.e("ADDED", mTimesAvailable.toString());
                }
            }

            @Override
            public void onItemClicked(RecyclerView recyclerView, TimeTableAdapter.ViewHolder mViewHolderTouched, int position) {
                Log.e("ItemTouchListener", "onItemClicked");
            }

            @Override
            public void onViewHolderHovered(RecyclerView rv, TimeTableAdapter.ViewHolder viewHolder) {
                Log.e("ItemTouchListener", "onViewHolderHovered");
                Integer position = viewHolder.getAdapterPosition();
                if (!blockSelected) {
                    viewHolder.btn.setBackgroundColor(Color.parseColor("#FFDFC7AF"));
                    viewHolder.selected = blockSelected;
                    if (mTimesAvailable.contains(position)) {
                        mTimesAvailable.remove(position);
                    }
                    Log.e("REMOVED", mTimesAvailable.toString());
                } else {
                    viewHolder.btn.setBackgroundColor(Color.RED);
                    viewHolder.selected = blockSelected;
                    mTimesAvailable.add(position);
                    Log.e("ADDED", mTimesAvailable.toString());
                }
            }

            @Override
            public void onMultipleViewHoldersSelected(RecyclerView rv, ArrayList<TimeTableAdapter.ViewHolder> selection) {
                Log.e("ItemTouchListener", "onMultipleViewHoldersSelected");
            }
        }));

        return view;
    }

    @Override
    public void onItemSelected(View view, int position) {

    }
}
