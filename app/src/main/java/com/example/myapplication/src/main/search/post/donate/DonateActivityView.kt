package com.example.myapplication.src.main.search.post.donate

interface DonateActivityView {
    fun onGetSupportSuccess(response: String)
    fun onGetSupportFail(message: String)
    fun onPostSupportSuccess(response: String)
    fun onPostSupportFail(message: String)
}