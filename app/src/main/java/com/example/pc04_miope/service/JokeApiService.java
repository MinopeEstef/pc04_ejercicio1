package com.example.pc04_miope.service;
import com.example.pc04_miope.entity.JokeApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JokeApiService {
    @GET("joke/Any")
    Call<JokeApiResponse> getJoke(@Query("category") String category, @Query("lang") String language);
}