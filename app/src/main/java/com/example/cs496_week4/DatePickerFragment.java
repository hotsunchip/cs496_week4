package com.example.cs496_week4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {
    public interface OnTimeSetInterface {
        void onTimeSet(int selectedHour, int selectedMinute, int mode);
    }

    // constants
    public static final String FRAGMENT_TAG = "DatePicker";
    public static final int START_TIME = 2;
    public static final int END_TIME = 0;
    public static final int CURRENT_TIME = 6;
    private final int DEFAULT_INTERVAL = 30;
    private final int MINUTES_MIN = 0;
    private final int MINUTES_MAX = 60;
    private final OnTimeSetInterface mListener;
    private int timeCode;
    private int mMode;

    // fields
    private int hour;
    private int min;

    public DatePickerFragment(int time, DatePickerFragment.OnTimeSetInterface listener) {
        this.timeCode = time;
        this.mListener = listener;
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
        AlertDialog.Builder adb = new AlertDialog.Builder(getContext());

        View view2 = LayoutInflater.from(getContext())
                .inflate(R.layout.time_picker_dialog, null);
        adb.setView(view2);

        AlertDialog mTimePickerDialog = adb.create();

        mTimePickerDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                TimePicker tp = mTimePickerDialog.findViewById(R.id.timePicker);
                tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                        hour = i;
                        min = i1;
                    }
                });
                ViewGroup tpLayout = (ViewGroup) tp;

                ViewGroup layout = (ViewGroup) tpLayout.getChildAt(0);
                LinearLayout llayout = (LinearLayout) layout.getChildAt(1);
                Log.e("ChildCount", String.valueOf(layout.getChildCount()));

                // Customize minute NumberPicker
                NumberPicker minutePicker = (NumberPicker) llayout.getChildAt(2);
                if (timeCode < 5) {
                    minutePicker.setMinValue(0);
                    minutePicker.setMaxValue(1);
                    minutePicker.setDisplayedValues(new String[]{"00", "30"});
                    minutePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                        @Override
                        public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                            min = i1;
                        }
                    });
                }

            }
        });
        final TextView buttonConfirm = (TextView) view2.findViewById(R.id.timePicker_confirm);
        final TextView buttonCancel = (TextView) view2.findViewById(R.id.timePicker_cancel);

        mTimePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mTimePickerDialog.setView(view2, 0, 0, 0, 0);
        mTimePickerDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onTimeSet(hour, min, timeCode);
                Log.e("Clicked", hour + "시" + min + "분");
                mTimePickerDialog.dismiss();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimePickerDialog.dismiss();
            }
        });
        return mTimePickerDialog;
    }
}
