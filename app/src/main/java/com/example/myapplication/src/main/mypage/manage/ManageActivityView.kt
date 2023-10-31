package com.example.myapplication.src.main.mypage.manage

interface ManageActivityView {
    fun onGetHostEventsSuccess(response: String)
    fun onGetHostEventsFail(message: String)
}