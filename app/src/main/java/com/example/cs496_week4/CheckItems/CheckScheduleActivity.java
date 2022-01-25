package com.example.cs496_week4.CheckItems;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cs496_week4.Data.SchedulePlace;
import com.example.cs496_week4.Main.CodeActivity;
import com.example.cs496_week4.Main.MainActivity;
import com.example.cs496_week4.R;

public class CheckScheduleActivity extends AppCompatActivity {
    // fields
    private String scheduleName;
    private String scheduleDate;
    private String scheduleTime;
    private SchedulePlace schedulePlace;
    private String destName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkschedule);


        Intent intent = getIntent();
        scheduleName = intent.getStringExtra("scheduleName");
        destName = intent.getStringExtra("destName");

        // set toolbar
        TextView title = findViewById(R.id.toolbar_title);
        title.setText(scheduleName);
        Toolbar toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Button check_dest = findViewById(R.id.check_dest_position);
        check_dest.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckScheduleActivity.this, CheckDestPosition.class);
                intent.putExtra("scheduleName", scheduleName);
                intent.putExtra("destName", destName);
                Log.d("destName3", destName);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
            case R.id.menu_delete:
                Log.e("Delete", "");
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu) ;

        return true ;
    }
}
