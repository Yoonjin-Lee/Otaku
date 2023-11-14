package com.example.myapplication.src.main.search.post.join.model

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class JoinData(
    @SerializedName("xNickname") val name: String,
    @SerializedName("xId") val id: String
)
