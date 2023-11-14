package com.example.myapplication.src.main.search.map

import android.net.Uri

data class MapData (
    val eventId : Int,
    val image : Uri,
    val title : String,
    val name : String,
    val id : String,
    val main : String,
    var heart : Boolean,
    val donation : Boolean,
    val latitude : Double,
    val longitude: Double
        )