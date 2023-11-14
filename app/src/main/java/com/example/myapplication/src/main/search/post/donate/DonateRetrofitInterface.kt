package com.example.myapplication.src.main.search.post.donate

import com.example.myapplication.src.main.search.post.donate.model.SupportData
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface DonateRetrofitInterface {
    @GET("/supports/info/{supportId}")
    fun getSupport(
        @Path(value = "supportId") supportId: Int
    ): Call<String>

    @Multipart
    @POST("/supports/{supportId}")
    fun postSupport(
        @Path(value = "supportId") supportId: Int,
        @Part perksImageFile: MultipartBody.Part,
        @Part("request") supportData: SupportData
    ): Call<String>
}