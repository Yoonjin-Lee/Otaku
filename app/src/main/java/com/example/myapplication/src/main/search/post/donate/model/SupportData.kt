package com.example.myapplication.src.main.search.post.donate.model

import com.google.gson.annotations.SerializedName

data class SupportData(
    @SerializedName("accountAddress") val accountAddress: String,
    @SerializedName("accountHolder") val accountHolder: String,
    @SerializedName("supportAmount") val money: Int
)