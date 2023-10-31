package com.example.myapplication.src.main.mypage.manage.manageDonation

interface ManageDonationActivityView {
    fun onGetSupportsSuccess(response: String)
    fun onGetSupportsFail(message: String)
    fun onPostSupportSuccess(response: String)
    fun onPostSupportFail(message: String)
}