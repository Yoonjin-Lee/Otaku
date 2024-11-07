package com.example.myapplication.src.main.heart

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HeartRetrofitInterface {
    @GET("/events")
    fun getWishEvents(@Query("is-wish-list") boolean: Boolean) : Call<String>
}