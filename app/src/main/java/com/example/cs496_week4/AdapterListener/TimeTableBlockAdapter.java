package com.example.cs496_week4.AdapterListener;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_week4.R;

import java.util.ArrayList;


public class TimeTableBlockAdapter extends RecyclerView.Adapter<TimeTableBlockAdapter.ViewHolder> {
    // interface
    public interface OnListItemSelectedInterface {
        void onItemSelected(View view, int position);
    }

    // fields
    private Context mContext;
    private ArrayList<Integer> mAvailable;
    private PlaceSearchAdapter.OnListItemSelectedInterface mListener;
    private int mSize;

    public TimeTableBlockAdapter(Context context, PlaceSearchAdapter.OnListItemSelectedInterface listener, int size, ArrayList<Integer> list) {
        super();

        // 생성자에서 데이터 리스트 객체를 전달받음.
        this.mContext = context;
        this.mListener = listener;
        this.mAvailable = list;
        this.mSize = size;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mAvailable.contains(position)) {
            holder.selected = true;
            holder.btn.setBackgroundColor(Color.parseColor("#FF51AF7E"));
        }
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.ntt_item_block, parent, false);
        return new ViewHolder(view);
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mSize;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        public View btn;
        public boolean selected = false;

        public ViewHolder(View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.layout_ntt_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    mListener.onItemSelected(view, position);
                    Log.d("test", "position = " + position);
                }
            });
        }
    }

}
/* public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.ViewHolder> {

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


    @Override
    public void onBindViewHolder(TimeTableAdapter.ViewHolder holder, int position) {
        holder.getLinearLayout().setLayoutParams(mData.get(position).getLayoutParams());
        holder.getLinearLayout().setBackgroundColor(Color.RED);
        if(position > 0) return;

        int num_block = mData.get(position).getChildCount();
        for (int i = 0; i < num_block; ++i) {
            holder.getLinearLayout().getChildAt(i).setLayoutParams(mData.get(position).getChildAt(i).getLayoutParams());
            holder.getLinearLayout().getChildAt(i).setBackgroundColor(Color.YELLOW);
        }
    }
} */