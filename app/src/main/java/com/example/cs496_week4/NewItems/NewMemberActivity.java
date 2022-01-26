package com.example.cs496_week4.NewItems;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.cs496_week4.Main.MainActivity;
import com.example.cs496_week4.R;
import com.example.cs496_week4.Retrofit.CallRetrofit;
import com.example.cs496_week4.Retrofit.Data.appt.Input__apptInvite;
import com.example.cs496_week4.Retrofit.Data.user.Output__allUsers;
import com.example.cs496_week4.Retrofit.Data.user.allUsersElement;

import java.util.ArrayList;

public class NewMemberActivity extends AppCompatActivity implements MemberSearchAdapter.OnListItemSelectedInterface {
    // fields
    private ArrayList<Member> searchMembers;
    // _layout
    private RecyclerView recyclerView_members;
    private int apptId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newmember);

        Intent intent = getIntent();
        apptId = intent.getIntExtra("apptId", -1);

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

        searchMembers = new ArrayList<>();
        // sample data
        Output__allUsers allUsers = new CallRetrofit().allUsers(MainActivity.userToken);
        allUsersElement[] infos = allUsers.getInfos();
        for(int i = 0; i < infos.length; ++i) {
            searchMembers.add(new Member(infos[i].getUserName(), infos[i].getUserEmail()));
        }


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
        new CallRetrofit().apptInvite(MainActivity.userToken, new Input__apptInvite(apptId, new String[]{"test0"}));
        view.findViewById(R.id.btn_member_add).setVisibility(View.GONE);
    }
}
