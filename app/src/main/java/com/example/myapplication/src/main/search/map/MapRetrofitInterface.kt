package com.example.myapplication.src.main.search.map

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MapRetrofitInterface {
    @GET("/events/list")
    fun getList(
        @Query("subjectId") subjectId : Int
    ): Call<String>
    @GET("/v2/local/search/address.json")
    fun getLocation(
        @Header("Authorization") key: String, // 카카오 API 인증키 [필수]
        @Query("query") query: String // 검색을 원하는 질의어 [필수]
    ):Call<String>
}