package com.example.cs496_week4.AdapterListener;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_week4.R;


public class TimeTableTimeAdapter extends RecyclerView.Adapter<TimeTableTimeAdapter.ViewHolder> {
    // fields
    private Context mContext;
    private String[] mList;

    public TimeTableTimeAdapter(Context context, String[] list) {
        super();

        // 생성자에서 데이터 리스트 객체를 전달받음.
        this.mContext = context;
        this.mList = list;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        setTimeTextView(holder.tvTime, mList[position]);
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.ntt_item_time, parent, false);
        return new ViewHolder(view);
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mList.length;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        protected View btn;
        protected TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.layout_ntt_item);
            tvTime = itemView.findViewById(R.id.ntt_position);
        }
    }

    private void setTimeTextView(TextView tv, String time) {
        String state = "AM";
        int hour = Integer.parseInt(time.substring(0, 2));
        if (hour > 11) {
            hour -= 12;
            state = "PM";
        }
        if (hour == 0) hour = 12;
        tv.setText(String.format("%02d", hour) + ":" + time.substring(2) + " " + state);
    }

}