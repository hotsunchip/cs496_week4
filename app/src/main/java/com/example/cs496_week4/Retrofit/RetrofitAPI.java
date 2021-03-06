package com.example.cs496_week4.Retrofit;

import com.example.cs496_week4.Retrofit.Data.appt.Input__apptCreate;
import com.example.cs496_week4.Retrofit.Data.appt.Input__apptInvite;
import com.example.cs496_week4.Retrofit.Data.appt.Model__editApptInfo;
import com.example.cs496_week4.Retrofit.Data.appt.Output__invitedAppts;
import com.example.cs496_week4.Retrofit.Data.map.Input__setAlarm;
import com.example.cs496_week4.Retrofit.Data.map.Output__setAlarm;
import com.example.cs496_week4.Retrofit.Data.user.GET__userDeparture;
import com.example.cs496_week4.Retrofit.Data.user.Input__signIn;
import com.example.cs496_week4.Retrofit.Data.user.Input__signUp;
import com.example.cs496_week4.Retrofit.Data.appt.Model__apptInfo;
import com.example.cs496_week4.Retrofit.Data.user.Output__Coordinate;
import com.example.cs496_week4.Retrofit.Data.user.Output__allUsers;
import com.example.cs496_week4.Retrofit.Data.user.Output__userApptsDate;
import com.example.cs496_week4.Retrofit.Data.user.Output__userWTMs;
import com.example.cs496_week4.Retrofit.Data.user.POST__userDeparture;
import com.example.cs496_week4.Retrofit.Data.wtm.Input__wtmCreate;
import com.example.cs496_week4.Retrofit.Data.appt.Model__apptAccept;
import com.example.cs496_week4.Retrofit.Data.appt.Model__apptDelete;
import com.example.cs496_week4.Retrofit.Data.appt.Model__apptReject;
import com.example.cs496_week4.Retrofit.Data.user.Model__resetPassword;
import com.example.cs496_week4.Retrofit.Data.user.Model__userEmailExists;
import com.example.cs496_week4.Retrofit.Data.user.Model__userNameExists;
import com.example.cs496_week4.Retrofit.Data.wtm.Input__wtmRespond;
import com.example.cs496_week4.Retrofit.Data.wtm.Model__wtmInfo;
import com.example.cs496_week4.Retrofit.Data.appt.Output__apptCreate;
import com.example.cs496_week4.Retrofit.Data.appt.Output__apptInvite;
import com.example.cs496_week4.Retrofit.Data.user.Output__signIn;
import com.example.cs496_week4.Retrofit.Data.user.Output__signUp;
import com.example.cs496_week4.Retrofit.Data.wtm.Output__wtmCreate;
import com.example.cs496_week4.Retrofit.Data.wtm.Output__wtmRespond;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @FormUrlEncoded
    @POST("appt/accept")
    Call<Model__apptAccept> postApptAccept(@Header("jwt") String token, @Field("apptId") int apptId);

    @FormUrlEncoded
    @POST("appt/reject")
    Call<Model__apptReject> postApptReject(@Header("jwt") String token, @Field("apptId") int apptId);

    //@GET("appt/members")
    //Call<Model__apptMembers> getApptMembers(@Header("jwt") String token, @Query("apptId") int apptId);

    @GET("appt/info")
    Call<Model__apptInfo> getApptInfo(@Header("jwt") String token, @Query("apptId") int apptId);

    @DELETE("appt/delete")
    Call<Model__apptDelete> deleteApptDelete(@Header("jwt") String token, @Query("apptId") int apptId);

    @GET("wtm/info")
    Call<Model__wtmInfo> getWtmInfo(@Header("jwt") String token, @Query("wtmId") int wtmId);

    @POST("wtm/respond")
    Call<Output__wtmRespond> postWtmRespond(@Header("jwt") String token, @Body Input__wtmRespond input);

    @FormUrlEncoded
    @POST("user/departure")
    Call<POST__userDeparture> postUserDeparture(@Header("jwt") String token, @Field("departure") float[] departure);

    @GET("user/departure")
    Call<GET__userDeparture> getUserDeparture(@Header("jwt") String token, @Query("username") String username);

    @GET("user/appts")
    Call<Output__userApptsDate> getUserAppts(@Header("jwt") String token);

    @GET("user/wtms")
    Call<Output__userWTMs> getUserWTMs(@Header("jwt") String token);

    @GET("user/appts-date")
    Call<Output__userApptsDate> getUserApptsDate(@Header("jwt") String token, @Query("date") String date);

    @POST("map/set-alarm")
    Call<Output__setAlarm> postSetAlarm(@Header("jwt") String token, @Body Input__setAlarm input);

    @GET("map/get-coordinate")
    Call<Output__Coordinate> getCoordinate(@Header("jwt") String token, @Query("address") String address);

    @PUT("appt/edit")
    Call<Model__editApptInfo> putAppUpdate(@Header("jwt") String token, @Query("apptId") int apptId, @Body Input__apptCreate input);

    @GET("user/allusers")
    Call<Output__allUsers> getAllUsers(@Header("jwt") String token);

    @GET("user/invited-appts")
    Call<Output__invitedAppts> getInvitedAppts(@Header("jwt") String token);

    //@FormUrlEncoded
    //@POST("/auth/overlapChecker")
    //Call<Model__CheckAlready> postOverlapCheck(@Field("phone") String phoneNum, @Field("message") String message); //?????? ????????? ??????????????? (*???????????? ?????????)
}