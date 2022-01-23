package com.example.cs496_week4.Retrofit;

import com.example.cs496_week4.Retrofit.Data.Input__apptCreate;
import com.example.cs496_week4.Retrofit.Data.Input__apptInvite;
import com.example.cs496_week4.Retrofit.Data.Input__signIn;
import com.example.cs496_week4.Retrofit.Data.Input__signUp;
import com.example.cs496_week4.Retrofit.Data.Input__wtmCreate;
import com.example.cs496_week4.Retrofit.Data.Model__resetPassword;
import com.example.cs496_week4.Retrofit.Data.Model__userEmailExists;
import com.example.cs496_week4.Retrofit.Data.Model__userNameExists;
import com.example.cs496_week4.Retrofit.Data.Output__apptCreate;
import com.example.cs496_week4.Retrofit.Data.Output__apptInvite;
import com.example.cs496_week4.Retrofit.Data.Output__signIn;
import com.example.cs496_week4.Retrofit.Data.Output__signUp;
import com.example.cs496_week4.Retrofit.Data.Output__wtmCreate;

import java.io.IOException;

import retrofit2.Call;

public class CallRetrofit {

    public boolean userNameExists(String username){

        //Retrofit 호출
        Model__userNameExists[] modelUserNameExists = new Model__userNameExists[1];
        Call<Model__userNameExists> call = RetrofitClient.getApiService().userNameOverlapCheck(username);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    modelUserNameExists[0] = call.execute().body();
                } catch(IOException ie) {
                    ie.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return modelUserNameExists[0].isResult();
    }

    public boolean userEmailExists(String userEmail){

        //Retrofit 호출
        Model__userEmailExists[] modelUserEmailExists = new Model__userEmailExists[1];
        Call<Model__userEmailExists> call = RetrofitClient.getApiService().userEmailOverlapCheck(userEmail);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    modelUserEmailExists[0] = call.execute().body();
                } catch(IOException ie) {
                    ie.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return modelUserEmailExists[0].isResult();
    }

    public Output__signUp signUp(Input__signUp input){

        //Retrofit 호출
        Output__signUp[] output = new Output__signUp[1];
        Call<Output__signUp> call = RetrofitClient.getApiService().postSignUp(input);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    output[0] = call.execute().body();
                } catch(IOException ie) {
                    ie.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return output[0];
    }

    public Output__signIn signIn(Input__signIn input){

        //Retrofit 호출
        Output__signIn[] output = new Output__signIn[1];
        Call<Output__signIn> call = RetrofitClient.getApiService().postSignIn(input);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    output[0] = call.execute().body();
                } catch(IOException ie) {
                    ie.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(700);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return output[0];
    }

    public boolean resetPassword(String token, String newPassword){

        //Retrofit 호출
        Model__resetPassword[] modelResetPassword = new Model__resetPassword[1];
        Call<Model__resetPassword> call = RetrofitClient.getApiService().postResetPassword(token, newPassword);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    modelResetPassword[0] = call.execute().body();
                } catch(IOException ie) {
                    ie.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return modelResetPassword[0].isResult();
    }

    public Output__wtmCreate wtmCreate(String token, Input__wtmCreate input){

        //Retrofit 호출
        Output__wtmCreate[] output = new Output__wtmCreate[1];
        Call<Output__wtmCreate> call = RetrofitClient.getApiService().postWtmCreate(token, input);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    output[0] = call.execute().body();
                } catch(IOException ie) {
                    ie.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return output[0];
    }

    public Output__apptCreate apptCreate(String token, Input__apptCreate input){

        //Retrofit 호출
        Output__apptCreate[] output = new Output__apptCreate[1];
        Call<Output__apptCreate> call = RetrofitClient.getApiService().postApptCreate(token, input);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    output[0] = call.execute().body();
                } catch(IOException ie) {
                    ie.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return output[0];
    }

    public Output__apptInvite apptInvite(String token, Input__apptInvite input){

        //Retrofit 호출
        Output__apptInvite[] output = new Output__apptInvite[1];
        Call<Output__apptInvite> call = RetrofitClient.getApiService().postApptInvite(token, input);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    output[0] = call.execute().body();
                } catch(IOException ie) {
                    ie.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return output[0];
    }
}