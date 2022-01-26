package com.example.cs496_week4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {
    public interface OnDateSetInterface {
        void onDateSet(int selectedYear, int selectedMonth, int selectedDate);
    }

    // constants
    public static final String FRAGMENT_TAG = "DatePicker";
    private final OnDateSetInterface mListener;

    // fields
    private int year;
    private int month;
    private int day;

    public DatePickerFragment(DatePickerFragment.OnDateSetInterface listener) {
        this.mListener = listener;
        // current time
        Calendar mCalendar = Calendar.getInstance();
        year = mCalendar.get(Calendar.YEAR);
        month = mCalendar.get(Calendar.MONTH);
        day = mCalendar.get(Calendar.DATE);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder adb = new AlertDialog.Builder(getContext());

        View view2 = LayoutInflater.from(getContext())
                .inflate(R.layout.date_picker_dialog, null);
        adb.setView(view2);

        AlertDialog mDatePickerDialog = adb.create();

        mDatePickerDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onShow(DialogInterface dialogInterface) {
                DatePicker tp = mDatePickerDialog.findViewById(R.id.timePicker);
                tp.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                        year = i;
                        month = i1;
                        day = i2;
                    }
                });
            }
        });
        final TextView buttonConfirm = (TextView) view2.findViewById(R.id.datePicker_confirm);
        final TextView buttonCancel = (TextView) view2.findViewById(R.id.datePicker_cancel);

        mDatePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mDatePickerDialog.setView(view2, 0, 0, 0, 0);
        mDatePickerDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onDateSet(year, month, day);
                mDatePickerDialog.dismiss();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatePickerDialog.dismiss();
            }
        });
        return mDatePickerDialog;
    }
}
