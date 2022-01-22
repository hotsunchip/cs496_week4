package com.example.cs496_week4.NewTimeTable;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.cs496_week4.R;

import java.util.ArrayList;

public class NewTimeTableActivity extends AppCompatActivity {
    // constant
    public static final int MAX_FRAG_NUM = 3;

    // fields
    public ArrayList<Fragment> fragments;
    public static int fragPos = 0;
    private Button btn_ntt_prev;
    private Button btn_ntt_next;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtimetable);


        Intent intent = getIntent();

        // set toolbar
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("New Time Table");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_24);
        }

        // set fragments for ntt
        initializeFragment();

        // set prev & next btn
        btn_ntt_prev = findViewById(R.id.btn_ntt_prev);
        btn_ntt_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragPos == MAX_FRAG_NUM - 1) btn_ntt_next.setVisibility(View.VISIBLE);
                if (fragPos > 0) fragPos -= 1;
                if (fragPos == 0) btn_ntt_prev.setVisibility(View.INVISIBLE);
                replaceFragment(fragPos, -1);
            }
        });
        btn_ntt_next = findViewById(R.id.btn_ntt_next);
        btn_ntt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragPos == 0) btn_ntt_prev.setVisibility(View.VISIBLE);
                if (fragPos < MAX_FRAG_NUM - 1) fragPos += 1;
                if (fragPos == MAX_FRAG_NUM - 1)  btn_ntt_next.setVisibility(View.INVISIBLE);
                replaceFragment(fragPos, 1);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (fragPos == MAX_FRAG_NUM - 1) {
                    Toast.makeText(NewTimeTableActivity.this,"내 타임테이블에서 확인 가능", Toast.LENGTH_LONG).show();
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

        Fragment1TimeTableName frag1 = new Fragment1TimeTableName();
        Fragment2TimeTableDate frag2 = new Fragment2TimeTableDate();
        Fragment3TimeTableDone frag3 = new Fragment3TimeTableDone();
        fragments.add(frag1);
        fragments.add(frag2);
        fragments.add(frag3);
        for (Fragment frag : fragments) {
            ft.add(R.id.frame_layout_ntt, frag);
            ft.hide(frag);
        }
        ft.show(frag1);
        ft.commit();
    }
}
