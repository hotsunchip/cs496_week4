package com.example.cs496_week4.Retrofit;

import android.util.Log;

import com.example.cs496_week4.Retrofit.Data.Input__signUp;
import com.example.cs496_week4.Retrofit.Data.Model__userEmailExists;
import com.example.cs496_week4.Retrofit.Data.Model__userNameExists;
import com.example.cs496_week4.Retrofit.Data.Output__signUp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallRetrofit {

    public boolean userNameExists(String username){

        //Retrofit 호출
        Model__userNameExists modelUserNameExists = new Model__userNameExists();
        Call<Model__userNameExists> call = RetrofitClient.getApiService().userNameOverlapCheck(username);
        call.enqueue(new Callback<Model__userNameExists>() {
            @Override
            public void onResponse(Call<Model__userNameExists> call, Response<Model__userNameExists> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                Log.d("연결이 성공적 : ", response.body().toString());
                modelUserNameExists.setResult(response.body().isResult());
            }
            @Override
            public void onFailure(Call<Model__userNameExists> call, Throwable t) {
                Log.e("연결실패", t.getMessage());
            }
        });

        return modelUserNameExists.isResult();
    }

    public boolean userEmailExists(String userEmail){

        //Retrofit 호출
        Model__userEmailExists modelUserEmailExists = new Model__userEmailExists();
        Call<Model__userEmailExists> call = RetrofitClient.getApiService().userEmailOverlapCheck(userEmail);
        call.enqueue(new Callback<Model__userEmailExists>() {
            @Override
            public void onResponse(Call<Model__userEmailExists> call, Response<Model__userEmailExists> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                Log.d("연결이 성공적 : ", response.body().toString());
                modelUserEmailExists.setResult(response.body().isResult());
            }
            @Override
            public void onFailure(Call<Model__userEmailExists> call, Throwable t) {
                Log.e("연결실패", t.getMessage());
            }
        });

        return modelUserEmailExists.isResult();
    }

    public Output__signUp signUp(Input__signUp input){

        //Retrofit 호출
        Output__signUp output = new Output__signUp();
        Call<Output__signUp> call = RetrofitClient.getApiService().postSignUp(input);
        call.enqueue(new Callback<Output__signUp>() {
            @Override
            public void onResponse(Call<Output__signUp> call, Response<Output__signUp> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                Log.d("연결이 성공적 : ", response.body().toString());
                output.setMessage(response.body().getMessage());
                output.setToken(response.body().getToken());
            }
            @Override
            public void onFailure(Call<Output__signUp> call, Throwable t) {
                Log.e("연결실패", t.getMessage());
            }
        });

        return output;
    }
}