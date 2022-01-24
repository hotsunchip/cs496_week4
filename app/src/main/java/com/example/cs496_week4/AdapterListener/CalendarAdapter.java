package com.example.cs496_week4.AdapterListener;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_week4.Data.CalendarItem;
import com.example.cs496_week4.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {
    private Context mContext;
    private ArrayList<CalendarItem> mList;
    private SearchRecyclerAdapter.OnListItemSelectedInterface mListener;
    private int mSelected;

    public interface OnListItemSelectedInterface {
        void onItemSelected(View view, int position);
    }

    public CalendarAdapter(Context context, SearchRecyclerAdapter.OnListItemSelectedInterface listener, ArrayList<CalendarItem> list, int selected_day) {
        super();

        this.mContext = context;
        this.mListener = listener;
        this.mList = list;
        this.mSelected = selected_day;
    }

    public void setSelected(int selected) {
        this.mSelected = selected;
    }

    @Override
    public CalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.calendar_list_item, parent, false);
        return new CalendarViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(CalendarViewHolder holder, int position) {
        holder.cd_date.setText(mList.get(position).getCd_date());
        holder.cd_day.setText(mList.get(position).getCd_day());

        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd").withLocale(Locale.forLanguageTag("ko")));

        holder.cd_card.setBackgroundResource(android.R.color.transparent);
        if (mSelected < 0) {
            if (today.equals(mList.get(position).getCd_date())) {
                holder.cd_date.setTextColor(R.color.off_white);
                holder.cd_day.setTextColor(R.color.off_white);
                holder.cd_card.setBackgroundResource(R.drawable.background_blue);
            }
        } else {
            if (today.equals(mList.get(position).getCd_date())) {
                if (mSelected == position) {
                    holder.cd_card.setBackgroundResource(R.drawable.background_blue);
                } else {

                    holder.cd_card.setBackgroundResource(R.drawable.background_blue_line);
                }
            } else {
                if (mSelected == position) {
                    holder.cd_card.setBackgroundResource(R.drawable.background_white);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    class CalendarViewHolder extends RecyclerView.ViewHolder {
        TextView cd_date;
        TextView cd_day;
        CardView cd_card;

        public CalendarViewHolder(View itemView) {
            super(itemView);
            cd_date = itemView.findViewById(R.id.tv_date);
            cd_day = itemView.findViewById(R.id.tv_day);
            cd_card = itemView.findViewById(R.id.card_view_week);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    mListener.onItemSelected(view, position);
//                    Log.d("test", "position = " + position);
                }
            });
        }
    }
}
