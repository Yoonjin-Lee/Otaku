package com.example.myapplication.src.main.home.add.addAccount.model

import com.google.gson.annotations.SerializedName

data class AccountData(
    @SerializedName("bank") val bank: String,
    @SerializedName("accountAddress") val accountAddress: String,
    @SerializedName("accountHolder") val accountHolder: String,
    @SerializedName("targetAmount") val targetAmount: Int
)
