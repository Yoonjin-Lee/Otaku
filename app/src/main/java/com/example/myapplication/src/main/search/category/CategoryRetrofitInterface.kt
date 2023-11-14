package com.example.myapplication.src.main.search.category

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryRetrofitInterface {
    @GET("/subjects")
    fun getCategory(
        @Query("page") page : Int,
        @Query("size") size : Int,
        @Query("category") title: String,
        @Query("lastSubjectId") last : Long
    ): Call<String>
}