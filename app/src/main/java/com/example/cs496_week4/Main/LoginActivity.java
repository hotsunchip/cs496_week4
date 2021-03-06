package com.example.cs496_week4.Main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cs496_week4.R;
import com.example.cs496_week4.Retrofit.CallRetrofit;
import com.example.cs496_week4.Retrofit.Data.user.Input__signIn;
import com.example.cs496_week4.Retrofit.Data.user.Input__signUp;
import com.example.cs496_week4.Retrofit.Data.user.Output__signIn;
import com.example.cs496_week4.Retrofit.Data.user.Output__signUp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    // fields
    private AutoCompleteTextView mIdView;
    private EditText mPasswordView;
    private EditText mNameView;
    private TextView mLoginJoinTxtBtn;
    private ImageButton mInButton;
    private ProgressBar mProgressView;
    private boolean mLoginMode;
    private String mName;
    private String mEmail;
    private String mPassWord;
    private CallRetrofit callRetrofit;

    private static String userName="";
    private static String userEmail="";
    private static String userToken="";
    private static boolean isNewComer=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = getIntent();

        // set login toolbar
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        // connect with items
        callRetrofit = new CallRetrofit();
        mLoginMode = true;
        mNameView = (EditText) findViewById(R.id.in_name);
        mIdView = (AutoCompleteTextView) findViewById(R.id.in_email);
        mPasswordView = (EditText) findViewById(R.id.in_password);
        mInButton = (ImageButton) findViewById(R.id.btn_next);
        mProgressView = (ProgressBar) findViewById(R.id.progress);
        mLoginJoinTxtBtn = (TextView) findViewById(R.id.login_mode);

        // add clickListeners
        mLoginJoinTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginMode = !mLoginMode;
                setLoginJoinMode();
            }
        });
        mInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLoginMode) {
                    attemptLogin();
                } else {
                    attemptJoin();
                }
            }
        });

        setLoginJoinMode();
        //moveMain();
        if (bringPrevLogin()) moveMain();
    }

    private void attemptLogin() {
        mIdView.setError(null);
        mPasswordView.setError(null);

        mEmail = mIdView.getText().toString();
        mPassWord = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // ??????????????? ????????? ??????
        if (mPassWord.isEmpty()) {
            mPasswordView.setError("??????????????? ??????????????????.");
            focusView = mPasswordView;
            cancel = true;
        } else if (!isPasswordValid(mPassWord)) {
            mPasswordView.setError("6??? ????????? ??????????????? ??????????????????.");
            focusView = mPasswordView;
            cancel = true;
        }
        // ???????????? ????????? ??????
        if (mPassWord.isEmpty()) {
            mIdView.setError("???????????? ??????????????????.");
            focusView = mIdView;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            startLogin(new Input__signIn(mEmail, mPassWord));
            showProgress(true);
        }
    }

    private void startLogin(Input__signIn data) {
        Output__signIn call_login = callRetrofit.signIn(data);
        String message = call_login.getMessage();
        userToken = call_login.getToken();
        userName = mName;
        userEmail = mEmail;
        Log.e("startLogin", call_login.toString());
        moveMain();
    }

    private void attemptJoin() {
        mNameView.setError(null);
        mIdView.setError(null);
        mPasswordView.setError(null);

        mName = mNameView.getText().toString();
        mEmail = mIdView.getText().toString();
        mPassWord = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // ??????????????? ????????? ??????
        if (mPassWord.isEmpty()) {
            mPasswordView.setError("??????????????? ??????????????????.");
            focusView = mPasswordView;
            cancel = true;
        } else if (!isPasswordValid(mPassWord)) {
            mPasswordView.setError("6??? ????????? ??????????????? ??????????????????.");
            focusView = mPasswordView;
            cancel = true;
        }

        // ???????????? ????????? ??????
        if (mEmail.isEmpty()) {
            mIdView.setError("???????????? ??????????????????.");
            focusView = mIdView;
            cancel = true;
        }

        // ????????? ????????? ??????
        if (mName.isEmpty()) {
            mNameView.setError("????????? ??????????????????.");
            focusView = mNameView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startJoin(new Input__signUp(mName, mEmail, mPassWord));
            showProgress(true);
        }
    }

    private void startJoin(Input__signUp data) {
        Output__signUp call_join = callRetrofit.signUp(data);
        String message = call_join.getMessage();
        userToken = call_join.getToken();
        userName = mName;
        userEmail = mEmail;
        Log.e("startJoin", call_join.toString());
        isNewComer = true;
        moveMain();
    }


    private void setLoginJoinMode() {
        if (mLoginMode) {
            mNameView.setVisibility(View.GONE);
            mLoginJoinTxtBtn.setText("????????????");
        } else {
            mNameView.setVisibility(View.VISIBLE);
            mLoginJoinTxtBtn.setText("?????????");
        }
    }

    private void moveMain() {
        mProgressView.setVisibility(View.GONE);
        Intent startApp = new Intent(this, MainActivity.class);
        startApp.putExtra("userName", userName);
        startApp.putExtra("userEmail", userEmail);
        startApp.putExtra("userToken", userToken);
        startApp.putExtra("isNewComer", isNewComer);
        startActivity(startApp);
        finish();
    }

    private boolean isPasswordValid(String password) {
        return true; // password.length() >= 6;
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void deletePrevLogin() {
        // ?????? ????????? ?????? ?????????
//        String fileName = "user.json";
//        File file = new File(getFilesDir(), fileName);
//        file.delete();
    }

    private boolean bringPrevLogin() {
        // ?????? ????????? ?????? ????????????
        return false;
    }
}