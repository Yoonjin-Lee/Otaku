package com.example.myapplication.config

import android.media.Image

data class PostData (
    val image : Int,
    val title : String,
    val name : String,
    val id : String,
    val main : String,
    val heart : Boolean,
    val donation : Boolean
        )