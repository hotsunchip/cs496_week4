package com.example.cs496_week4.AdapterListener;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_week4.Data.ScheduleItem;
import com.example.cs496_week4.R;

import java.util.ArrayList;


public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    // interface
    public interface OnListItemSelectedInterface {
        void onScheduleItemSelected(View view, int position);
    }

    // fields
    private Context mContext;
    private OnListItemSelectedInterface mListener;
    private ArrayList<ScheduleItem> mList;

    public ScheduleAdapter(Context context, OnListItemSelectedInterface listener, ArrayList<ScheduleItem> list) {
        super();

        // 생성자에서 데이터 리스트 객체를 전달받음.
        this.mContext = context;
        this.mListener = listener;
        this.mList = list;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(mList.get(position).getScheduleName());
        holder.tvDate.setText(mList.get(position).getScheduleDate());
        holder.tvTime.setText(mList.get(position).getScheduleTime());
        holder.tvPlace.setText(mList.get(position).getSchedulePlace());
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.schedule_item, parent, false);
        return new ViewHolder(view);
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mList.size();
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvName;
        protected TextView tvDate;
        protected TextView tvTime;
        protected TextView tvPlace;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_schedule_item_name);
            tvDate = itemView.findViewById(R.id.tv_schedule_item_date);
            tvTime = itemView.findViewById(R.id.tv_schedule_item_time);
            tvPlace = itemView.findViewById(R.id.tv_schedule_item_place);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onScheduleItemSelected(itemView, getAdapterPosition());
                }
            });
        }
    }

}