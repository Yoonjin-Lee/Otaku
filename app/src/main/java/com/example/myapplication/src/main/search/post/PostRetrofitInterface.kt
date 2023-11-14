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

    @POST("/events/report/{eventId}")
    fun postReport(
        @Path(value = "eventId") eventId: Int
    ): Call<String>

    @POST("/event-logs/expected")
    fun postEventPublic(
        @Query("eventId") eventId: Int
    ): Call<String>

    @GET("/v2/local/search/address.json")
    fun getLocation(
        @Header("Authorization") key: String, // 카카오 API 인증키 [필수]
        @Query("query") query: String // 검색을 원하는 질의어 [필수]
    ):Call<String>

    @GET("/subjects/all-list")
    fun getAllSubjects(): Call<String>
}