package com.example.cs496_week4.Main;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_week4.Data.CalendarItem;
import com.example.cs496_week4.R;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Locale;

import com.example.cs496_week4.AdapterListener.CalendarAdapter;

public class Fragment1WeekCalender extends Fragment implements CalendarAdapter.OnListItemSelectedInterface {
    // fields
    private TextView calendarMonthYear;
    private RecyclerView calendarWeek;
    private CalendarAdapter calendarAdapter;
    private String[] week_day;
    private ArrayList<CalendarItem> calendarList;
    private DateTimeFormatter dateFormat;
    private DateTimeFormatter monthFormat;
    private String localDate;
    private int selectedDay;

    // Required empty public constructor
    public Fragment1WeekCalender() {

    }

    public static Fragment1WeekCalender newInstance() {
        Fragment1WeekCalender fragment = new Fragment1WeekCalender();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 변수 초기화
        week_day = getResources().getStringArray(R.array.calendar_day);
        selectedDay = -1;

        dateFormat = DateTimeFormatter.ofPattern("dd").withLocale(Locale.forLanguageTag("ko"));
        monthFormat = DateTimeFormatter.ofPattern("yyyy년 MM월").withLocale(Locale.forLanguageTag("ko"));
        localDate = LocalDateTime.now().format(monthFormat);
        LocalDateTime preSunday = LocalDateTime.now().with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
        calendarList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Log.d("날짜", week_day[i]);

            calendarList.add(new CalendarItem(preSunday.plusDays((long) i).format(dateFormat), week_day[i]));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_1, container, false);

        calendarMonthYear = view.findViewById(R.id.tv_cd_YearMonth);
        calendarMonthYear.setText(localDate);
        calendarWeek = view.findViewById(R.id.recycler_view_calendar);
        calendarAdapter = new CalendarAdapter(getContext(), this::onItemSelected, calendarList, selectedDay);
        calendarWeek.setAdapter(calendarAdapter);
        calendarWeek.setLayoutManager(new GridLayoutManager(getContext(), 7));

        return view;
    }

    @Override
    public void onItemSelected(View view, int position) {
        calendarAdapter.setSelected(position);
        calendarWeek.setAdapter(calendarAdapter);
    }
}
