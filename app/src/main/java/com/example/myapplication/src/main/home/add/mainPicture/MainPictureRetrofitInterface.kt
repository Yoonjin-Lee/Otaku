package com.example.myapplication.src.main.home.add.mainPicture

import com.example.myapplication.src.main.home.add.mainPicture.model.Request
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface MainPictureRetrofitInterface {
    @Multipart
    @POST("/events")
    fun postEvent(
        @Part featuredImageFile: MultipartBody.Part,
        @Part perksImageFile: MultipartBody.Part,
        @Part("request") request: Request
    ): Call<String>
}