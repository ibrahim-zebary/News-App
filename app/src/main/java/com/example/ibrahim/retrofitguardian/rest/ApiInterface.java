package com.example.ibrahim.retrofitguardian.rest;

import com.example.ibrahim.retrofitguardian.model.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("search?api-key=eaadbdac-e23c-4211-9961-5b9a8b4163fb&show-fields=byline,thumbnail")
    Call<Root> getRoot(@Query("q")
                       String query);
}
