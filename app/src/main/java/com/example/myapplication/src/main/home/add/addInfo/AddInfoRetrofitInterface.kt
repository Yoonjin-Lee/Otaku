package com.example.myapplication.src.main.home.add.addInfo

import com.example.myapplication.src.main.home.add.addInfo.model.CategoryData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AddInfoRetrofitInterface {
    @GET("/subjects/all-list")
    fun getSubjects():Call<String>
    @POST("/subjects")
    fun postSubject(
        @Body categoryData: CategoryData
    ): Call<String>
}