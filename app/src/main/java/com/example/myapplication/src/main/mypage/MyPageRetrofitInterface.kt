package com.example.myapplication.src.main.mypage

import retrofit2.Call
import retrofit2.http.POST

interface MyPageRetrofitInterface {
    @POST("/users/withdrawal")
    fun postLogout():Call<String>
}