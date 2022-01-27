package com.example.cs496_week4.NewItems;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_week4.AdapterListener.DragSelectionItemTouchListener;
import com.example.cs496_week4.AdapterListener.LongPressItemTouchListener;
import com.example.cs496_week4.AdapterListener.TimeTableBlockAdapter;
import com.example.cs496_week4.AdapterListener.TimeTableTimeAdapter;
import com.example.cs496_week4.R;

import java.util.ArrayList;
import java.util.Arrays;

public class FragmentTimeTableAll extends Fragment implements TimeTableBlockAdapter.OnListItemSelectedInterface{
    // fields
    private int num_day;
    private int num_block; // if (time_end < time_start) time_end += 24 * 2
    private int time_start;
    private int time_end;
    private boolean blockSelected;
    // _values
    private int height = -1;
    private int width = -1;
    private int block_height = 100;
    private int block_width = 160;
    private int layout_height;
    private int layout_width;
    // _layout elements
    private RecyclerView time_table_block;
    private RecyclerView time_table_time;
    private LinearLayout.LayoutParams layout_params;
    // _data
    ArrayList<Integer> mTimesAvailable;
    ArrayList<Integer> mTimesSample;
    private String[] time_list;
    private String[] timeRange_day;
    private String time_start_string;
    private String time_end_string;

    // Required empty public constructor
    public FragmentTimeTableAll(int days, String start, String end) {
        num_day = days;
        time_start_string = start;
        time_end_string = end;

    }

    public static FragmentTimeTableAll newInstance(int days, String start, String end) {
        FragmentTimeTableAll fragment = new FragmentTimeTableAll(days, start, end);
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

        timeRange_day = getResources().getStringArray(R.array.timeRange_day);
        time_start = Arrays.binarySearch(timeRange_day, time_start_string);
        time_end = Arrays.binarySearch(timeRange_day, time_end_string);

        if (time_end < time_start) {
            String[] arr1 = Arrays.copyOfRange(timeRange_day, time_start, timeRange_day.length - 1);
            String[] arr2 = Arrays.copyOfRange(timeRange_day, 0, time_end + 1);
            time_list = new String[arr1.length + arr2.length];
            System.arraycopy(arr1, 0, time_list, 0, arr1.length);
            System.arraycopy(arr2, 0, time_list, arr1.length, arr2.length);
            time_end += 48;
        } else {
            time_list = Arrays.copyOfRange(timeRange_day, time_start, time_end + 1);
        }
        num_block = time_end - time_start;
        Log.e("time index", num_block +" vs " + time_list.length);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tt, container, false);
        time_table_block = view.findViewById(R.id.recycler_view_table);
        time_table_time = view.findViewById(R.id.recycler_view_time);
//        scroll_left = view.findViewById(R.id.scroll_left);
//        scroll_right = view.findViewById(R.id.scroll_right);

        mTimesAvailable = new ArrayList<Integer>();
//        mTimeBtns = new ArrayList<View>();
//        for (int k = 0; k < num_block * num_day; k ++) mTimeBtns.add(new View(getContext()));
//        time_table_block.post(new Runnable() {
//            @Override
//            public void run() {
//                height = time_table_block.getMeasuredHeight();
//                width = time_table_block.getMeasuredWidth();
//            }
//        });
//
//        if (height != -1 && width != -1) {
//            layout_height = block_height;
//            layout_width = block_width;
//            layout_params = new LinearLayout.LayoutParams(layout_width, layout_height);
//        } else {
//            layout_params = new LinearLayout.LayoutParams(block_width, block_height);
//        }


        time_table_time.setLayoutManager(new LinearLayoutManager(getActivity()));
        TimeTableTimeAdapter timeAdapter = new TimeTableTimeAdapter(getContext(), time_list);
        time_table_time.setAdapter(timeAdapter);


        // 리사이클러뷰에 GridLayoutManager 객체 지정.
        time_table_block.setLayoutManager(new GridLayoutManager(getActivity(), num_day, GridLayoutManager.VERTICAL, false));

        // 리사이클러Fragment1WeekCalender뷰에 SimpleTextAdapter 객체 지정.
        TimeTableBlockAdapter blockAdapter = new TimeTableBlockAdapter(getContext(), this::onItemSelected, num_block * num_day, mTimesSample);
        time_table_block.setAdapter(blockAdapter);

        time_table_block.addOnItemTouchListener(new DragSelectionItemTouchListener(getContext(), new LongPressItemTouchListener.OnItemInteractionListener() {

            @Override
            public void onLongItemClicked(RecyclerView recyclerView, TimeTableBlockAdapter.ViewHolder mViewHolderTouched, int position) {
                Log.e("ItemTouchListener", "onLongItemClicked");
                Integer pos = position;
                if (mViewHolderTouched.selected) {
                    blockSelected = false;
                    mViewHolderTouched.btn.setBackgroundColor(Color.parseColor("#FFF0F0F0"));
                    mViewHolderTouched.selected = blockSelected;
                    if (mTimesAvailable.contains(position)) {
                        mTimesAvailable.remove(pos);
                    }
                    Log.e("REMOVED", mTimesAvailable.toString());
                } else {
                    blockSelected = true;
                    mViewHolderTouched.btn.setBackgroundColor(Color.parseColor("#FF51AF7E"));
                    mViewHolderTouched.selected = blockSelected;
                    if (!mTimesAvailable.contains(pos)) {
                        mTimesAvailable.add(pos);
                    }
                    Log.e("ADDED", mTimesAvailable.toString());
                }
            }

            @Override
            public void onItemClicked(RecyclerView recyclerView, TimeTableBlockAdapter.ViewHolder mViewHolderTouched, int position) {
                Log.e("ItemTouchListener", "onItemClicked");
            }

            @Override
            public void onViewHolderHovered(RecyclerView rv, TimeTableBlockAdapter.ViewHolder viewHolder) {
                Log.e("ItemTouchListener", "onViewHolderHovered");
                Integer position = viewHolder.getAdapterPosition();
                if (!blockSelected) {
                    viewHolder.btn.setBackgroundColor(Color.parseColor("#FFF0F0F0"));
                    viewHolder.selected = blockSelected;
                    if (mTimesAvailable.contains(position)) {
                        mTimesAvailable.remove(position);
                    }
                    Log.e("REMOVED", mTimesAvailable.toString());
                } else {
                    viewHolder.btn.setBackgroundColor(Color.parseColor("#FF51AF7E"));
                    viewHolder.selected = blockSelected;
                    if (!mTimesAvailable.contains(position)) {
                        mTimesAvailable.add(position);
                    }
                    Log.e("ADDED", mTimesAvailable.toString());
                }
            }

            @Override
            public void onMultipleViewHoldersSelected(RecyclerView rv, ArrayList<TimeTableBlockAdapter.ViewHolder> selection) {
                Log.e("ItemTouchListener", "onMultipleViewHoldersSelected");
            }
        }));

        return view;
    }

    @Override
    public void onItemSelected(View view, int position) {

    }
}
