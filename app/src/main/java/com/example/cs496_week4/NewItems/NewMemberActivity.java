package com.example.cs496_week4.NewItems;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_week4.AdapterListener.MemberSearchAdapter;
import com.example.cs496_week4.Data.Member;
import com.example.cs496_week4.R;

import java.util.ArrayList;

public class NewMemberActivity extends AppCompatActivity implements MemberSearchAdapter.OnListItemSelectedInterface {
    // fields
    private ArrayList<Member> newMembers;
    private ArrayList<Member> searchMembers;
    // _layout
    private RecyclerView recyclerView_members;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newmember);

        Intent intent = getIntent();

        // set toolbar
        TextView title = findViewById(R.id.toolbar_title);
        title.setText("멤버 추가하기");
        Toolbar toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        newMembers = new ArrayList<>();
        searchMembers = new ArrayList<>();
        // sample data
        searchMembers.add(new Member("cc", "cc@kaist.ac.kr"));
        searchMembers.add(new Member("cc", "cc@kaist.ac.kr"));
        searchMembers.add(new Member("cc", "cc@kaist.ac.kr"));
        searchMembers.add(new Member("cc", "cc@kaist.ac.kr"));
        searchMembers.add(new Member("cc", "cc@kaist.ac.kr"));


        recyclerView_members = findViewById(R.id.recycler_view_nmb);
        MemberSearchAdapter searchAdapter = new MemberSearchAdapter(this, this, searchMembers);
        recyclerView_members.setAdapter(searchAdapter);
        recyclerView_members.setLayoutManager(new LinearLayoutManager(this));


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

    @Override
    public void onItemSelected(View view, String name, String email) {
        Member newMem = new Member(name, email);
        if (!newMembers.contains(newMem)) {
            newMembers.add(newMem);
        }
    }
}
