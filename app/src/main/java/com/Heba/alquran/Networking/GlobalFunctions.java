package com.Heba.alquran.Networking;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GlobalFunctions {
    public static Retrofit getAppRetrofit(Context context) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
        return new Retrofit.Builder()
                .baseUrl("http://api.alquran.cloud")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }
   // reqFile = RequestBody.create(MediaType.parse("application/pdf"), new File(pdfPath));

}

//////////////////


