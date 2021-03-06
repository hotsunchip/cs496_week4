package com.example.cs496_week4.Retrofit;

import android.util.Log;

import com.example.cs496_week4.Retrofit.Data.appt.Input__apptCreate;
import com.example.cs496_week4.Retrofit.Data.appt.Input__apptInvite;
import com.example.cs496_week4.Retrofit.Data.appt.Model__editApptInfo;
import com.example.cs496_week4.Retrofit.Data.appt.Output__invitedAppts;
import com.example.cs496_week4.Retrofit.Data.map.Input__setAlarm;
import com.example.cs496_week4.Retrofit.Data.map.Output__setAlarm;
import com.example.cs496_week4.Retrofit.Data.user.GET__userDeparture;
import com.example.cs496_week4.Retrofit.Data.user.Input__signIn;
import com.example.cs496_week4.Retrofit.Data.user.Input__signUp;
import com.example.cs496_week4.Retrofit.Data.appt.Model__apptAccept;
import com.example.cs496_week4.Retrofit.Data.appt.Model__apptDelete;
import com.example.cs496_week4.Retrofit.Data.appt.Model__apptInfo;
import com.example.cs496_week4.Retrofit.Data.appt.Model__apptReject;
import com.example.cs496_week4.Retrofit.Data.user.Output__Coordinate;
import com.example.cs496_week4.Retrofit.Data.user.Output__allUsers;
import com.example.cs496_week4.Retrofit.Data.user.Output__userApptsDate;
import com.example.cs496_week4.Retrofit.Data.user.Output__userWTMs;
import com.example.cs496_week4.Retrofit.Data.user.POST__userDeparture;
import com.example.cs496_week4.Retrofit.Data.wtm.Input__wtmCreate;
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

import java.io.IOException;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public class CallRetrofit {

    public boolean userNameExists(String username){

        //Retrofit ??????
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

        //Retrofit ??????
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

        //Retrofit ??????
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
            Thread.sleep(700);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return output[0];
    }

    public Output__signIn signIn(Input__signIn input){

        //Retrofit ??????
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

        //Retrofit ??????
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

        //Retrofit ??????
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

        //Retrofit ??????
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

        //Retrofit ??????
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

    public Model__wtmInfo wtmInfo(String token, int wtmId){

        //Retrofit ??????
        Model__wtmInfo[] output = new Model__wtmInfo[1];
        Call<Model__wtmInfo> call = RetrofitClient.getApiService().getWtmInfo(token, wtmId);
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

    public Model__apptAccept apptAccept(String token, int apptId){

        //Retrofit ??????
        Model__apptAccept[] output = new Model__apptAccept[1];
        Call<Model__apptAccept> call = RetrofitClient.getApiService().postApptAccept(token, apptId);
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

    public Model__apptReject apptReject(String token, int apptId){

        //Retrofit ??????
        Model__apptReject[] output = new Model__apptReject[1];
        Call<Model__apptReject> call = RetrofitClient.getApiService().postApptReject(token, apptId);
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

    public Model__apptInfo apptInfo(String token, int apptId){

        //Retrofit ??????
        Model__apptInfo[] output = new Model__apptInfo[1];
        Call<Model__apptInfo> call = RetrofitClient.getApiService().getApptInfo(token, apptId);
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

    public Model__apptDelete apptDelete(String token, int apptId){

        //Retrofit ??????
        Model__apptDelete[] output = new Model__apptDelete[1];
        Call<Model__apptDelete> call = RetrofitClient.getApiService().deleteApptDelete(token, apptId);
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

    public Output__wtmRespond wtmRespond(String token, Input__wtmRespond input){

        //Retrofit ??????
        Output__wtmRespond[] output = new Output__wtmRespond[1];
        Call<Output__wtmRespond> call = RetrofitClient.getApiService().postWtmRespond(token, input);
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

    public POST__userDeparture postUserDeparture(String token, float[] input){

        //Retrofit ??????
        POST__userDeparture[] output = new POST__userDeparture[1];
        Call<POST__userDeparture> call = RetrofitClient.getApiService().postUserDeparture(token, input);
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

    public GET__userDeparture getUserDeparture(String token, String username){

        //Retrofit ??????
        GET__userDeparture[] output = new GET__userDeparture[1];
        Call<GET__userDeparture> call = RetrofitClient.getApiService().getUserDeparture(token, username);
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

    public Output__userApptsDate userApptsDate(String token, String date){

        //Retrofit ??????
        Output__userApptsDate[] output = new Output__userApptsDate[1];
        Call<Output__userApptsDate> call = RetrofitClient.getApiService().getUserApptsDate(token, date);
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


    public Output__userWTMs allUserWTMs(String token){
        //Retrofit ??????
        Output__userWTMs[] output = new Output__userWTMs[1];
        Call<Output__userWTMs> call = RetrofitClient.getApiService().getUserWTMs(token);
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

    public Output__userApptsDate allUserAppts(String token){
        //Retrofit ??????
        Output__userApptsDate[] output = new Output__userApptsDate[1];
        Call<Output__userApptsDate> call = RetrofitClient.getApiService().getUserAppts(token);
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


    public Output__setAlarm setAlarm(String token, Input__setAlarm input){

        //Retrofit ??????
        Output__setAlarm[] output = new Output__setAlarm[1];
        Call<Output__setAlarm> call = RetrofitClient.getApiService().postSetAlarm(token, input);
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

    public Output__Coordinate convertAddressToCoordinate(String token, String address) {

        Output__Coordinate[] output = new Output__Coordinate[1];
        Call<Output__Coordinate> call = RetrofitClient.getApiService().getCoordinate(token, address);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    output[0] = call.execute().body();
                }
                catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return output[0];
    }

    public Model__editApptInfo updateAppt(String token, int apptId, Input__apptCreate apptInfo) {

        Model__editApptInfo[] output = new Model__editApptInfo[1];
        Call<Model__editApptInfo> call = RetrofitClient.getApiService().putAppUpdate(token, apptId, apptInfo);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    output[0] = call.execute().body();
                }
                catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return output[0];
    }

    public Output__allUsers allUsers(String token) {

        Output__allUsers[] output = new Output__allUsers[1];
        Call<Output__allUsers> call = RetrofitClient.getApiService().getAllUsers(token);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    output[0] = call.execute().body();
                }
                catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return output[0];
    }

    public Output__invitedAppts invitedAppts(String token) {

        Output__invitedAppts[] output = new Output__invitedAppts[1];
        Call<Output__invitedAppts> call = RetrofitClient.getApiService().getInvitedAppts(token);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    output[0] = call.execute().body();
                }
                catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return output[0];
    }
}