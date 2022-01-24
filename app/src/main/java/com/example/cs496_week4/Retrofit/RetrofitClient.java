package com.example.cs496_week4.Retrofit;

import com.example.cs496_week4.Retrofit.Data.appt.apptInfo_members;
import com.example.cs496_week4.Retrofit.Data.user.userApptsDate_owned;
import com.example.cs496_week4.Retrofit.Data.wtm.wtmInfo_personalResponses;
import com.example.cs496_week4.Retrofit.Data.wtm.wtmInfo_personalResponses_times;
import com.example.cs496_week4.Retrofit.Data.wtm.wtmRespond_times;
import com.example.cs496_week4.Retrofit.Data.wtm.wtmRespond_wtm;
import com.example.cs496_week4.Retrofit.Data.wtm.wtmRespond_wtm_times;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://192.249.18.146/";

    public static RetrofitAPI getApiService(){return getInstance().create(RetrofitAPI.class);}

    private static Retrofit getInstance(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .registerTypeAdapter(wtmInfo_personalResponses.class, new wtmInfo_personalResponses_Deserializer())
                .registerTypeAdapter(wtmInfo_personalResponses_times.class, new wtmInfo_personalResponses_times_Deserializer())
                .registerTypeAdapter(apptInfo_members.class, new apptInfo_members_element_Deserializer())
                .registerTypeAdapter(wtmRespond_times.class, new wtmRespond_times_Deserializer())
                .registerTypeAdapter(wtmRespond_wtm.class, new wtmRespond_wtm_Deserializer())
                .registerTypeAdapter(wtmRespond_wtm_times.class, new wtmRespond_wtm_times_Deserializer())
                .registerTypeAdapter(userApptsDate_owned.class, new userApptsDate_owned_Deserializer())
                .create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private static class wtmInfo_personalResponses_Deserializer implements JsonDeserializer<wtmInfo_personalResponses>
    {
        @Override
        public wtmInfo_personalResponses deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
                throws JsonParseException
        {
            // Get the "content" element from the parsed JSON
            JsonElement content = je.getAsJsonObject().get("personalResponses");

            // Deserialize it. You use a new instance of Gson to avoid infinite recursion
            // to this deserializer
            return new Gson().fromJson(content, wtmInfo_personalResponses.class);

        }
    }

    private static class wtmInfo_personalResponses_times_Deserializer implements JsonDeserializer<wtmInfo_personalResponses_times>
    {
        @Override
        public wtmInfo_personalResponses_times deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
                throws JsonParseException
        {
            // Get the "content" element from the parsed JSON
            JsonElement content = je.getAsJsonObject().get("times");

            // Deserialize it. You use a new instance of Gson to avoid infinite recursion
            // to this deserializer
            return new Gson().fromJson(content, wtmInfo_personalResponses_times.class);

        }
    }

    private static class apptInfo_members_element_Deserializer implements JsonDeserializer<apptInfo_members>
    {
        @Override
        public apptInfo_members deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
                throws JsonParseException
        {
            // Get the "content" element from the parsed JSON
            JsonElement content = je.getAsJsonObject().get("members");

            // Deserialize it. You use a new instance of Gson to avoid infinite recursion
            // to this deserializer
            return new Gson().fromJson(content, apptInfo_members.class);

        }
    }

    private static class wtmRespond_times_Deserializer implements JsonDeserializer<wtmRespond_times>
    {
        @Override
        public wtmRespond_times deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
                throws JsonParseException
        {
            // Get the "content" element from the parsed JSON
            JsonElement content = je.getAsJsonObject().get("times");

            // Deserialize it. You use a new instance of Gson to avoid infinite recursion
            // to this deserializer
            return new Gson().fromJson(content, wtmRespond_times.class);

        }
    }

    private static class wtmRespond_wtm_Deserializer implements JsonDeserializer<wtmRespond_wtm>
    {
        @Override
        public wtmRespond_wtm deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
                throws JsonParseException
        {
            // Get the "content" element from the parsed JSON
            JsonElement content = je.getAsJsonObject().get("wtm");

            // Deserialize it. You use a new instance of Gson to avoid infinite recursion
            // to this deserializer
            return new Gson().fromJson(content, wtmRespond_wtm.class);

        }
    }

    private static class wtmRespond_wtm_times_Deserializer implements JsonDeserializer<wtmRespond_wtm_times>
    {
        @Override
        public wtmRespond_wtm_times deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
                throws JsonParseException
        {
            // Get the "content" element from the parsed JSON
            JsonElement content = je.getAsJsonObject().get("times");

            // Deserialize it. You use a new instance of Gson to avoid infinite recursion
            // to this deserializer
            return new Gson().fromJson(content, wtmRespond_wtm_times.class);

        }
    }

    private static class userApptsDate_owned_Deserializer implements JsonDeserializer<userApptsDate_owned>
    {
        @Override
        public userApptsDate_owned deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
                throws JsonParseException
        {
            // Get the "content" element from the parsed JSON
            JsonElement content = je.getAsJsonObject().get("owned");

            // Deserialize it. You use a new instance of Gson to avoid infinite recursion
            // to this deserializer
            return new Gson().fromJson(content, userApptsDate_owned.class);

        }
    }
}