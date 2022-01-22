package com.example.cs496_week4.Retrofit;

import com.example.cs496_week4.Retrofit.Data.Input__signUp;
import com.example.cs496_week4.Retrofit.Data.Model__userEmailExists;
import com.example.cs496_week4.Retrofit.Data.Model__userNameExists;
import com.example.cs496_week4.Retrofit.Data.Output__signUp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitAPI {

    @GET("auth/userNameExists")
    Call<Model__userNameExists> userNameOverlapCheck(@Query("username") String username);

    @GET("auth/userEmailExists")
    Call<Model__userEmailExists> userEmailOverlapCheck(@Query("userEmail") String userEmail);

    @POST("auth/sign-up")
    Call<Output__signUp> postSignUp(@Body Input__signUp inputSignUp);

    @POST("auth/sign-in")
    Call<Output__signUp> postSignIn(@Body Input__signUp inputSignIn);

    //@FormUrlEncoded
    //@POST("/auth/overlapChecker")
    //Call<Model__CheckAlready> postOverlapCheck(@Field("phone") String phoneNum, @Field("message") String message); //이건 요청시 사용하는거 (*데이터를 보낼때)
}