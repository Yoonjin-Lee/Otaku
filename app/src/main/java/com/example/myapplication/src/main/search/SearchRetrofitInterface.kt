package com.example.myapplication.src.main.search

import retrofit2.Call
import retrofit2.http.GET

interface SearchRetrofitInterface {
    @GET("/subjects/all-list")
    fun getAllSubjects(): Call<String>
}