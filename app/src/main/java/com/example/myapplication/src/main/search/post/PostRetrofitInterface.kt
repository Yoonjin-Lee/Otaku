package com.example.myapplication.src.main.search.post

import retrofit2.Call
import retrofit2.http.*

interface PostRetrofitInterface {
    @GET("/events/{eventId}")
    fun getEvent(@Path(value = "eventId") eventId: Int) : Call<String>
    @POST("/wish-list/enroll")
    fun postWishList(@Query("eventId") eventId : Int) : Call<String>
    @DELETE("/wish-list/cancel")
    fun postWishCancel(@Query("eventId") eventId: Int) : Call<String>
}