package com.example.playground.mvvm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface APIInterface {
//    @GET("/api/unknown")
//    Call<List<HolidayModel>> getHolidays(@Query("page") Integer page);

    @GET("PublicHolidays/2019/us")
    Call<List<HolidayModel>> getHolidays();
}