package com.example.myapplication.src.main.heart

import retrofit2.Call
import retrofit2.http.GET

interface HeartFragmentView {
    fun onGetWishEventsSuccess(response : String)
    fun onGetWishEventsFail(message : String)
}