package com.example.cs496_week4.Main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_week4.Data.ScheduleItem;
import com.example.cs496_week4.R;
import com.example.cs496_week4.Retrofit.CallRetrofit;

import java.util.ArrayList;

public class SimpleTextAdapter extends RecyclerView.Adapter<SimpleTextAdapter.ViewHolder> {

    private ArrayList<ScheduleItem> mData = null ;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_schedule_item_date ;
        TextView tv_schedule_item_time ;
        TextView tv_schedule_item_place ;
        TextView tv_schedule_item_name ;

        Button reject;
        Button accept;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            tv_schedule_item_date = itemView.findViewById(R.id.tv_schedule_item_date) ;
            tv_schedule_item_time = itemView.findViewById(R.id.tv_schedule_item_time) ;
            tv_schedule_item_place = itemView.findViewById(R.id.tv_schedule_item_place) ;
            tv_schedule_item_name = itemView.findViewById(R.id.tv_schedule_item_name) ;

            reject = itemView.findViewById(R.id.reject) ;
            accept = itemView.findViewById(R.id.accept) ;
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    SimpleTextAdapter(ArrayList<ScheduleItem> list) {
        mData = list ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public SimpleTextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.new_invitations_item, parent, false) ;
        SimpleTextAdapter.ViewHolder vh = new SimpleTextAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(SimpleTextAdapter.ViewHolder holder, int position) {
        ScheduleItem element = mData.get(position) ;
        holder.tv_schedule_item_place.setText(element.getSchedulePlace()) ;
        holder.tv_schedule_item_name.setText(element.getScheduleName()) ;
        String date = element.getScheduleDate();
        String scheduleDate = date.substring(5, 7)+"월 "+date.substring(8, 10)+"일 ";
        holder.tv_schedule_item_date.setText(scheduleDate) ;
        int hour = Integer.parseInt(date.substring(11, 13));
        String state = " AM";
        if(hour > 11) {
            hour -= 12;
            state = " PM";
        }
        if(hour == 0 && state.equals(" PM")) hour = 12;
        String minute = date.substring(14, 16);
        holder.tv_schedule_item_time.setText(Integer.toString(hour)+":"+minute+state+" ") ;

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CallRetrofit().apptAccept(MainActivity.userToken, element.getApptId());
                holder.itemView.setVisibility(View.GONE);
            }
        });

        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CallRetrofit().apptReject(MainActivity.userToken, element.getApptId());
                holder.itemView.setVisibility(View.GONE);
            }
        });
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }
}