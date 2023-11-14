package com.example.myapplication.src.main.mypage.present

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PresentRetrofitInterface {
    @GET("/events/perks-image/{eventId}")
    fun getImage(@Path("eventId") eventId: Int) : Call<String>
}