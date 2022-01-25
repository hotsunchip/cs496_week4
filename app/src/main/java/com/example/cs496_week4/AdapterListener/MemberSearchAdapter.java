package com.example.cs496_week4.AdapterListener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_week4.Data.Member;
import com.example.cs496_week4.Data.SchedulePlace;
import com.example.cs496_week4.R;

import java.util.ArrayList;

public class MemberSearchAdapter extends RecyclerView.Adapter<MemberSearchAdapter.SearchResultViewHolder> {

    public interface OnListItemSelectedInterface {
        void onItemSelected(View view, String name, String email);
    }

    // fields
    private ArrayList<Member> mSearchResult;
    private Context mContext;
    private OnListItemSelectedInterface mListener;


    public MemberSearchAdapter(Context context, OnListItemSelectedInterface listener, ArrayList<Member> result) {
        this.mContext = context;
        this.mListener = listener;
        this.mSearchResult = result;
    }
    @NonNull
    @Override
    public MemberSearchAdapter.SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.member_item, parent, false);
//        Log.e("onCreateViewHolder", String.valueOf(true));
        return new MemberSearchAdapter.SearchResultViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberSearchAdapter.SearchResultViewHolder holder, int position) {
        String memName = mSearchResult.get(position).getMemName();
        String memEmail = mSearchResult.get(position).getMemEmail();
        holder.tv_member_name.setText(memName);
        holder.tv_member_email.setText(memEmail);

        holder.btn_member_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemSelected(view, memName, memEmail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSearchResult.size();
    }

    protected class SearchResultViewHolder extends RecyclerView.ViewHolder {
        TextView tv_member_name;
        TextView tv_member_email;
        Button btn_member_add;
        public SearchResultViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_member_name = itemView.findViewById(R.id.tv_member_name);
            tv_member_email = itemView.findViewById(R.id.tv_member_email);
            btn_member_add = itemView.findViewById(R.id.btn_member_add);
        }
    }
}
