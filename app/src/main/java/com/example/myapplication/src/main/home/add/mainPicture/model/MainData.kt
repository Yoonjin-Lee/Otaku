package com.example.myapplication.src.main.home.add.mainPicture.model

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class MainData(
    @SerializedName("featuredImageFile") val featuredImageFile: MultipartBody.Part,
    @SerializedName("perksImageFile") val perksImageFile: MultipartBody.Part,
    @SerializedName("request") val request: Request
)
