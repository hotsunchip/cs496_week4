package com.example.cs496_week4.NewSchedule;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cs496_week4.NewTimeTable.Fragment1TimeTableName;
import com.example.cs496_week4.NewTimeTable.Fragment2TimeTableDate;
import com.example.cs496_week4.NewTimeTable.Fragment3TimeTableDone;
import com.example.cs496_week4.NewTimeTable.NewTimeTableActivity;
import com.example.cs496_week4.R;

import java.util.ArrayList;

public class NewScheduleActivity extends AppCompatActivity {
    // fields
    private boolean mIsGroup;
    private int fragNum;
    public ArrayList<Fragment> fragments;
    public static int fragPos;
    private Button btn_nsd_prev;
    private Button btn_nsd_next;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newschedule);


        Intent intent = getIntent();
        mIsGroup = intent.getBooleanExtra("isGroup", false);

        // set toolbar
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("New Schedule");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_24);
        }

        // set fragments for nsd

        fragPos = 0;
        initializeFragment();

        // set prev & next btn
        btn_nsd_prev = findViewById(R.id.btn_nsd_prev);
        btn_nsd_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragPos == fragNum - 1) btn_nsd_next.setVisibility(View.VISIBLE);
                if (fragPos > 0) fragPos -= 1;
                if (fragPos == 0) btn_nsd_prev.setVisibility(View.INVISIBLE);
                replaceFragment(fragPos, -1);
            }
        });
        btn_nsd_next = findViewById(R.id.btn_nsd_next);
        btn_nsd_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragPos == 0) btn_nsd_prev.setVisibility(View.VISIBLE);
                if (fragPos < fragNum - 1) fragPos += 1;
                if (fragPos == fragNum - 1)  btn_nsd_next.setVisibility(View.INVISIBLE);
                replaceFragment(fragPos, 1);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (fragPos == fragNum - 1) {
                    Toast.makeText(NewScheduleActivity.this,"일정 목록에서 확인 가능", Toast.LENGTH_LONG).show();
                }
                this.finish();
                break;
        }
        return true;
    }

    public void replaceFragment(int fragmentPos, int direction) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (direction < 0) {
            ft.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_left, R.anim.exit_to_right);
        } else {
            ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_right, R.anim.exit_to_left);
        }
        ft.addToBackStack(null);
        ft.hide(fragments.get(fragmentPos - direction));
        ft.show(fragments.get(fragmentPos));
        ft.commit();
    }
    private void initializeFragment() {
        fragments = new ArrayList<Fragment>();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        if (mIsGroup) {
            fragNum = 3;
            Fragment1_1ScheduleNameMem frag1 = new Fragment1_1ScheduleNameMem();
            Fragment2ScheduleDate frag2 = new Fragment2ScheduleDate();
            Fragment3SchedulePlace frag3 = new Fragment3SchedulePlace();
            fragments.add(frag1);
            fragments.add(frag2);
            fragments.add(frag3);
        } else {
            fragNum = 4;
            Fragment1TimeTableName frag1 = new Fragment1TimeTableName();
            Fragment2ScheduleDate frag2 = new Fragment2ScheduleDate();
            Fragment3SchedulePlace frag3 = new Fragment3SchedulePlace();
            Fragment4ScheduleDone frag4 = new Fragment4ScheduleDone();
            fragments.add(frag1);
            fragments.add(frag2);
            fragments.add(frag3);
            fragments.add(frag4);
        }
        for (Fragment frag : fragments) {
            ft.add(R.id.frame_layout_nsd, frag);
            ft.hide(frag);
        }
        ft.show(fragments.get(0));
        ft.commit();
    }
}
