package com.example.myapplication.src.main.search.result

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ResultRetrofitInterface {
    @GET("/events")
    fun getSubject(
        @Query("subject") subject: String
    ): Call<String>

    @GET("/subjects/all-list")
    fun getAllSubjects(): Call<String>
}