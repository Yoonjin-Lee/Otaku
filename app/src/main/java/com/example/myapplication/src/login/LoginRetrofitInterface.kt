package com.example.myapplication.src.login

import com.example.myapplication.src.login.models.PostSignUpRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRetrofitInterface {
    @POST("/auth/users/sign-up")
    fun postSignUp(@Body params : PostSignUpRequest):Call<String>
}