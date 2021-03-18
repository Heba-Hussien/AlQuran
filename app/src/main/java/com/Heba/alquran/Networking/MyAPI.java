package com.Heba.alquran.Networking;
import com.Heba.alquran.Modules.ApiResponse;
import com.Heba.alquran.Modules.*;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;



public interface MyAPI {


    @Headers("Content-Type: application/json")
    @GET("/v1/quran/ar.husary")
    Call<ApiResponse> GetData(
    );


}

