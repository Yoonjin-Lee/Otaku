package com.example.myapplication.src.main.mypage.manage.manageDonation

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ManageDonationRetrofitInterface {
    @GET("/supports/{supportId}")
    fun getSupports(@Path(value = "supportId") supportId: Int): Call<String>
    @POST("/supports/{supportId}")
    fun postSupport(@Path(value = "supportId") supportId: Int): Call<String>
}