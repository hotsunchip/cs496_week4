package com.example.cs496_week4.Retrofit;

import com.example.cs496_week4.Retrofit.Data.appt.apptInfo_members;
import com.example.cs496_week4.Retrofit.Data.wtm.wtmInfo_personalResponses;
import com.example.cs496_week4.Retrofit.Data.wtm.wtmInfo_personalResponses_times;
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
}