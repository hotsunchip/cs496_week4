package com.example.cs496_week4.CheckItems;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_week4.AdapterListener.ScheduleAdapter;
import com.example.cs496_week4.AdapterListener.TimeTableAdapter;
import com.example.cs496_week4.Data.TimeTableItem;
import com.example.cs496_week4.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class CheckAllTimeTablesActivity extends AppCompatActivity implements FragmentCheckAll.SetAdapterInterface, TimeTableAdapter.OnListItemSelectedInterface {
    // fields
    private ArrayList<TimeTableItem> invitedList;
    private ArrayList<TimeTableItem> acceptedList;
    private ArrayList<TimeTableItem> ownedList;
    private ArrayList<Fragment> fragments;
    private ArrayList<RecyclerView.Adapter> adapters;
    private int fragPos;
    // _layout
    private TabLayout tabLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkall);


        Intent intent = getIntent();

        // set toolbar
        TextView title = findViewById(R.id.toolbar_title);
        title.setText("내 티티 모아보기");
        Toolbar toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // lists
        invitedList = new ArrayList<>();
        acceptedList = new ArrayList<>();
        ownedList = new ArrayList<>();

        // tab
        FragmentCheckAll frag1 = new FragmentCheckAll(this, this, FragmentCheckAll.TIMETABLE_INVITED);
        FragmentCheckAll frag2 = new FragmentCheckAll(this, this, FragmentCheckAll.TIMETABLE_ACCEPTED);
        FragmentCheckAll frag3 = new FragmentCheckAll(this, this, FragmentCheckAll.TIMETABLE_OWNED);
        fragPos = 0;
        fragments = new ArrayList<Fragment>();
        fragments.add(frag1);
        fragments.add(frag2);
        fragments.add(frag3);
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("초대된 티티"),true);
        tabLayout.addTab(tabLayout.newTab().setText("수락한 티티"));
        tabLayout.addTab(tabLayout.newTab().setText("내가 만든 티티"));
        replaceFragment(frag1);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition())
                {
                    case 0 :
                        fragPos = 0;
                        replaceFragment(fragments.get(fragPos));
                        break;
                    case 1 :
                        fragPos = 1;
                        replaceFragment(fragments.get(fragPos));
                        break;
                    case 2 :
                        fragPos = 2;
                        replaceFragment(fragments.get(fragPos));
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        tabLayout.getTabAt(0).select();

        adapters = new ArrayList<>();
        adapters.add(new TimeTableAdapter(this, this, invitedList));
        adapters.add(new TimeTableAdapter(this, this, acceptedList));
        adapters.add(new TimeTableAdapter(this, this, ownedList));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return true;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout_check_all, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    @Override
    public void onScheduleItemSelected(View view, int position) {
    }

    @Override
    public void setItemAdapter(RecyclerView recyclerView) {
        recyclerView.setAdapter(adapters.get(fragPos));
    }

    @Override
    public void refreshAdapter() {

    }
}
