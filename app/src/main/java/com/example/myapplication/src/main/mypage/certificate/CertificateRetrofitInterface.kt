package com.example.myapplication.src.main.mypage.certificate

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface CertificateRetrofitInterface {
    @Multipart
    @POST("/users/host-application")
    fun postHost(@Part image: MultipartBody.Part): Call<String>
}