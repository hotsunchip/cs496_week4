package com.example.cs496_week4.Main;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_week4.AdapterListener.ScheduleAdapter;
import com.example.cs496_week4.Data.CalendarItem;
import com.example.cs496_week4.Data.ScheduleItem;
import com.example.cs496_week4.R;

import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Locale;

import com.example.cs496_week4.AdapterListener.CalendarAdapter;
import com.example.cs496_week4.Retrofit.CallRetrofit;
import com.example.cs496_week4.Retrofit.Data.user.Output__userApptsDate;
import com.example.cs496_week4.Retrofit.Data.user.userApptsDate_owned;

public class FragmentWeekCalendar extends Fragment implements CalendarAdapter.OnListItemSelectedInterface, ScheduleAdapter.OnListItemSelectedInterface {
    // fields
    private DateTimeFormatter dateFormat;
    private int selectedDay;
    private String[] week_day;
    private ArrayList<CalendarItem> calendarList;
    private ArrayList<ScheduleItem> scheduleList;
    // _layout
    private RecyclerView calendarWeek;
    private RecyclerView daySchedule;
    private CalendarAdapter calendarAdapter;
    private ScheduleAdapter scheduleAdapter;

    // Required empty public constructor
    public FragmentWeekCalendar() {

    }

    public static FragmentWeekCalendar newInstance() {
        FragmentWeekCalendar fragment = new FragmentWeekCalendar();
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
        LocalDateTime preSunday = LocalDateTime.now().with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
        calendarList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            calendarList.add(new CalendarItem(preSunday.plusDays((long) i).format(dateFormat), week_day[i]));
        }

        /*CallRetrofit callRetrofit = new CallRetrofit();
        scheduleList = new ArrayList<ScheduleItem>();
        String yearNMonth = MainActivity.localDate.replace("년 ", "-").replace("월", "-");
        for(int i=0; i<7; i++) {
            String yyyymmdd = yearNMonth + calendarList.get(i).getCd_date();
            Log.d("TESTTTTTTYYMMDD", yyyymmdd);
            Output__userApptsDate result = callRetrofit.userApptsDate(MainActivity.userToken, yyyymmdd);
            Log.d("TESTTTTT", String.valueOf(result.getOwned().size()));

            for(userApptsDate_owned owned:result.getOwned()) {
                Log.d("TESTTTTT0", owned.toString());
                ScheduleItem item = new ScheduleItem();
                item.setScheduleDate(yyyymmdd);
                item.setScheduleName(owned.getName());
                item.setSchedulePlace(owned.getPlace());
                item.setScheduleTime(owned.getTime().substring(11, 15));
                scheduleList.add(item);
                Log.d("TESTTTTT", item.toString());
            }

            for(userApptsDate_owned accepted:result.getAccepted()) {
                Log.d("TESTTTTT0", accepted.toString());
                ScheduleItem item = new ScheduleItem();
                item.setScheduleDate(yyyymmdd);
                item.setScheduleName(accepted.getName());
                item.setSchedulePlace(accepted.getPlace());
                item.setScheduleTime(accepted.getTime().substring(11, 15));
                scheduleList.add(item);
                Log.d("TESTTTTT", item.toString());
            }
        }*/


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_week_calendar, container, false);

        // 주간 달력
        calendarWeek = view.findViewById(R.id.recycler_view_calendar);
        calendarAdapter = new CalendarAdapter(getContext(), this::onItemSelected, calendarList, selectedDay);
        calendarWeek.setAdapter(calendarAdapter);
        calendarWeek.setLayoutManager(new GridLayoutManager(getContext(), 7));

        // 하루 일정 표시
        daySchedule = view.findViewById(R.id.recycler_view_schedule_day);
        scheduleAdapter = new ScheduleAdapter(getContext(), this::onScheduleItemSelected, scheduleList);

        return view;
    }

    @Override
    public void onItemSelected(View view, int position) {
        calendarAdapter.setSelected(position);
        calendarWeek.setAdapter(calendarAdapter);
    }

    @Override
    public void onScheduleItemSelected(View view, int position) {

    }
}
