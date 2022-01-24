package com.example.cs496_week4;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    // constants
    public static final String FRAGMENT_TAG = "TimePicker";
    public static final int START_TIME = 2;
    public static final int END_TIME = 0;
    public static final int CURRENT_TIME = 6;
    private final int DEFAULT_INTERVAL = 30;
    private final int MINUTES_MIN = 0;
    private final int MINUTES_MAX = 60;
    private int timeCode;

    // fields
    private final int hour;
    private final int min;

    public TimePickerFragment(int time) {
        this.timeCode = time;
        if (timeCode > 5) {
            // current time
            Calendar mCalendar = Calendar.getInstance();
            hour = mCalendar.get(Calendar.HOUR_OF_DAY);
            min = mCalendar.get(Calendar.MINUTE);
            Log.e("time", String.valueOf(hour));
            Log.e("time", String.valueOf(min));
        } else {
            if (timeCode > 0) {
                // start time
                hour = 16;
                min = 0;
            } else {
                // end time
                hour = 22;
                min = 0;
            }
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        TimePickerDialog mTimePickerDialog = new TimePickerDialog(
                getContext(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar, this, hour, min, false
        );
        mTimePickerDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                int tpLayoutId = getResources().getIdentifier("timePickerLayout", "id", "android");

                ViewGroup tpLayout = (ViewGroup) mTimePickerDialog.findViewById(tpLayoutId);
                ViewGroup layout = (ViewGroup) tpLayout.getChildAt(0);

                // Customize minute NumberPicker
                NumberPicker minutePicker = (NumberPicker) layout.getChildAt(2);
                if (timeCode < 5) {
                    minutePicker.setMinValue(0);
                    minutePicker.setMaxValue(1);
                    minutePicker.setDisplayedValues(new String[]{"00", "30"});
                }
                minutePicker.setOnValueChangedListener(null);
            }
        });
        mTimePickerDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        return mTimePickerDialog;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourDay, int minute) {

    }
}
