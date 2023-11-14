package com.example.myapplication.src.main.mypage.manage

import android.net.Uri

data class ManageData(
    val eventId : Int,
    val supportId: Int?,
    val image : Uri,
    val title : String,
    val name : String,
    val id : String,
    val main : String,
    val code: Int
)
