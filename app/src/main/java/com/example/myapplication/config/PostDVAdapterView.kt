package com.example.myapplication.config

interface PostDVAdapterView {
    fun onPostWishListSuccess(response : String)
    fun onPostWishListFail(message : String)
    fun onPostWishCancelSuccess(response: String)
    fun onPostWishCancelFail(message: String)
}