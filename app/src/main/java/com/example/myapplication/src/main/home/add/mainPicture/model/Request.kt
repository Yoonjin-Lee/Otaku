package com.example.myapplication.src.main.home.add.mainPicture.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate


data class Request(
    @SerializedName("isPublic") val isPublic : Boolean,
    @SerializedName("xNickname") val xNickname: String,
    @SerializedName("xId") val xId: String,
    @SerializedName("name") val name: String,
    @SerializedName("subjectId") val subjectId: Int,
    @SerializedName("openedDate") val openedDate: LocalDate,
    @SerializedName("closedDate") val closedDate: LocalDate,
    @SerializedName("address") val address: String,
    @SerializedName("description") val description: String
)

