package com.example.cs496_week4.CheckItems;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_week4.Main.FragmentExample;
import com.example.cs496_week4.R;

import java.util.Set;

public class FragmentCheckAll extends Fragment {
    // interface
    public interface SetAdapterInterface {
        public void setItemAdapter(RecyclerView recyclerView);
        public void refreshAdapter();
    }
    // fields
    private Context mContext;
    private SetAdapterInterface mListener;


    // constants
    public static int SCHEDULE_INVITED = 0;
    public static int SCHEDULE_ACCEPTED = 1;
    public static int SCHEDULE_OWNED = 2;
    public static int TIMETABLE_INVITED = 10;
    public static int TIMETABLE_ACCEPTED = 11;
    public static int TIMETABLE_OWNED = 12;
    // fields
    private static int mMode;
    // _layout
    private TextView tv_description;
    private RecyclerView recyclerView_items;

    // Required empty public constructor
    public FragmentCheckAll(Context context, SetAdapterInterface listener, int mode) {
        this.mContext = context;
        this.mListener = listener;
        this.mMode = mode;
    }

    public static FragmentCheckAll newInstance(Context context, SetAdapterInterface listener, int mode) {
        FragmentCheckAll fragment = new FragmentCheckAll(context, listener, mode);
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
        View view = inflater.inflate(R.layout.fragment_check_all_tab, container, false);

        recyclerView_items = view.findViewById(R.id.recycler_view_check_all);
        mListener.setItemAdapter(recyclerView_items);

        // 대응하는 어댑터 연결~

        return view;
    }
}