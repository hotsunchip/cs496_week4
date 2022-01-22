package com.example.cs496_week4.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cs496_week4.NewSchedule.NewScheduleActivity;
import com.example.cs496_week4.NewTimeTable.NewTimeTableActivity;
import com.example.cs496_week4.R;

public class CodeActivity extends AppCompatActivity {
    // fields
    private boolean isTT;
    private TextView tv_code;
    private TextView tv_new;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        Intent intent = getIntent();
        this.isTT = intent.getBooleanExtra("isTT", false);

        // set toolbar
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // act differently by each tv
        tv_code = findViewById(R.id.tv_code);
        tv_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (isTT) {
                    intent = new Intent(CodeActivity.this, NewTimeTableActivity.class);
                } else {
                    intent = new Intent(CodeActivity.this, NewScheduleActivity.class);
                    intent.putExtra("isGroup", true);
                }
                startActivity(intent);
                finish();

            }
        });
        tv_new = findViewById(R.id.tv_new);
        tv_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (isTT) {
                    intent = new Intent(CodeActivity.this, NewTimeTableActivity.class);
                } else {
                    intent = new Intent(CodeActivity.this, NewScheduleActivity.class);
                }
                startActivity(intent);
                finish();
            }
        });
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
}
