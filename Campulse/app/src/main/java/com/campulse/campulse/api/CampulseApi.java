package com.campulse.campulse.api;

import com.campulse.campulse.model.Event;
import com.campulse.campulse.model.EventResponse;
import com.campulse.campulse.model.Success;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Eduardo Coronado on 9/12/2016.
 */
public interface CampulseApi {
    @GET("allevents/{date}")
    Call<EventResponse> loadEvents(@Path("date") String date);

    @GET("test/")
    Call<Success> ping();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://54.163.154.36:9000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
            .build();
}
