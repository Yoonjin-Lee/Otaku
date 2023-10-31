package com.example.myapplication.config

import android.media.Image
import android.net.Uri

data class PostData (
    val eventId : Int,
    val image : Uri,
    val title : String,
    val name : String,
    val id : String,
    val main : String,
    var heart : Boolean,
    val donation : Boolean
        )