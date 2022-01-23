package com.example.cs496_week4.NewItems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_week4.R;

import java.util.ArrayList;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.SearchResultViewHolder> {

    public interface OnListItemSelectedInterface {
        void onItemSelected(View view, int position);
    }

    // fields
    private ArrayList<SchedulePlace> mSearchResult;
    private Context mContext;


    public SearchRecyclerAdapter(Context context, ArrayList<SchedulePlace> result) {
        this.mContext = context;
        this.mSearchResult = result;
    }
    @NonNull
    @Override
    public SearchRecyclerAdapter.SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.nsd_place_item, parent, false);
//        Log.e("onCreateViewHolder", String.valueOf(true));
        return new SearchRecyclerAdapter.SearchResultViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecyclerAdapter.SearchResultViewHolder holder, int position) {
        holder.tv_place_name.setText(mSearchResult.get(position).getPlaceName());
        holder.tv_place_address.setText(mSearchResult.get(position).getPlaceAddress());
    }

    @Override
    public int getItemCount() {
        return mSearchResult.size();
    }

    protected class SearchResultViewHolder extends RecyclerView.ViewHolder {
        TextView tv_place_name;
        TextView tv_place_address;
        public SearchResultViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_place_name = itemView.findViewById(R.id.tv_place_name);
            tv_place_address = itemView.findViewById(R.id.tv_place_address);
        }
    }
}
