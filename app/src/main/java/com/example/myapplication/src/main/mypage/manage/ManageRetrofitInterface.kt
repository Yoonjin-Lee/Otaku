package com.example.myapplication.src.main.mypage.manage

import retrofit2.Call
import retrofit2.http.GET

interface ManageRetrofitInterface {
    @GET("/events/host")
    fun getHostEvents():Call<String>
}