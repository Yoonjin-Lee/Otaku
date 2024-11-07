package com.example.myapplication.src.main.home

import retrofit2.Call
import retrofit2.http.GET

interface HomeRetrofitInterface {
    @GET("/events")
    fun getEvents() : Call<String>
}