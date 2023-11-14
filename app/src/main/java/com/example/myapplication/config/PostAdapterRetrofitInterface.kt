package com.example.myapplication.config

import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Query

interface PostAdapterRetrofitInterface {
    @POST("/wish-list/enroll")
    fun postWishList(@Query("eventId") eventId : Int) : Call<String>
    @DELETE("/wish-list/cancel")
    fun postWishCancel(@Query("eventId") eventId: Int) : Call<String>
}