package com.example.cs496_week4.Main;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_week4.R;

import java.util.ArrayList;

public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.ViewHolder> {

    private ArrayList<LinearLayout> mData = null;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;

        ViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView;
        }

        public LinearLayout getLinearLayout() {
            return linearLayout;
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    TimeTableAdapter(ArrayList<LinearLayout> list) {
        this.mData = new ArrayList<LinearLayout>(list);
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public TimeTableAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout layout = new LinearLayout(parent.getContext());
        layout.setLayoutParams(mData.get(0).getLayoutParams());

        int num_block = mData.get(0).getChildCount();
        for (int i = 0; i < num_block; ++i) {
            LinearLayout block = new LinearLayout(parent.getContext());
            block.setLayoutParams(mData.get(0).getChildAt(i).getLayoutParams());
            layout.addView(block);
        }

        return new ViewHolder(layout);
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(TimeTableAdapter.ViewHolder holder, int position) {
        holder.getLinearLayout().setLayoutParams(mData.get(position).getLayoutParams());
        holder.getLinearLayout().setBackgroundColor(Color.RED);

        int num_block = mData.get(position).getChildCount();
        for (int i = 0; i < num_block; ++i) {
            holder.getLinearLayout().getChildAt(i).setLayoutParams(mData.get(position).getChildAt(i).getLayoutParams());
            holder.getLinearLayout().getChildAt(i).setBackgroundColor(Color.YELLOW);
        }
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size();
    }
}