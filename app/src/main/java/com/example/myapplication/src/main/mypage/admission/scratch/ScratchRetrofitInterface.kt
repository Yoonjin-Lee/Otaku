package com.example.myapplication.src.main.mypage.admission.scratch

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ScratchRetrofitInterface {
    @GET("/events/perks-image/{eventId}")
    fun getEventImage(@Path(value = "eventId") eventId : Int) : Call<String>
}