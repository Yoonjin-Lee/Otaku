package com.example.myapplication.src.main.search.post

interface PostActivityView {
    fun onGetEventSuccess(response: String)
    fun onGetEventFail(message: String)
    fun onPostWishListSuccess(response : String)
    fun onPostWishListFail(message : String)
    fun onPostWishCancelSuccess(response: String)
    fun onPostWishCancelFail(message: String)
}