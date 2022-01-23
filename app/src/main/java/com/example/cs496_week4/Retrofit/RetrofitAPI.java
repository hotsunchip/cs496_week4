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

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitAPI {

    @GET("auth/userNameExists")
    Call<Model__userNameExists> userNameOverlapCheck(@Query("username") String username);

    @GET("auth/userEmailExists")
    Call<Model__userEmailExists> userEmailOverlapCheck(@Query("userEmail") String userEmail);

    @POST("auth/sign-up")
    Call<Output__signUp> postSignUp(@Body Input__signUp input);

    @POST("auth/sign-in")
    Call<Output__signIn> postSignIn(@Body Input__signIn input);

    @FormUrlEncoded
    @POST("auth/reset-password")
    Call<Model__resetPassword> postResetPassword(@Header("jwt") String token, @Field("newPassword") String newPassword);

    @POST("wtm/create")
    Call<Output__wtmCreate> postWtmCreate(@Header("jwt") String token, @Body Input__wtmCreate input);

    @POST("appt/create")
    Call<Output__apptCreate> postApptCreate(@Header("jwt") String token, @Body Input__apptCreate input);

    @POST("appt/invite")
    Call<Output__apptInvite> postApptInvite(@Header("jwt") String token, @Body Input__apptInvite input);

    //@FormUrlEncoded
    //@POST("/auth/overlapChecker")
    //Call<Model__CheckAlready> postOverlapCheck(@Field("phone") String phoneNum, @Field("message") String message); //이건 요청시 사용하는거 (*데이터를 보낼때)
}