package com.example.cs496_week4.AdapterListener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_week4.R;

import java.util.ArrayList;


public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.ViewHolder>{

    Context context;
    ArrayList<View> list;

    public TimeTableAdapter(Context context, ArrayList<View> list) {
        super();

        // 생성자에서 데이터 리스트 객체를 전달받음.
        this.context = context;
        this.list = list;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.image.setImageResource(list.get(position).image);
//        holder.name.setText(list.get(position).name);
    }
    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ntt_item, parent, false);
        return new ViewHolder(view);
    }
    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return list.size();
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder{
        public View btn;
        public boolean selected = false;

        public ViewHolder(View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.layout_ntt_item);
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