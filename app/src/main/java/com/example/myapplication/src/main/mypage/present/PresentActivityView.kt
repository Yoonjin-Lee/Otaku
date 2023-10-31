package com.example.myapplication.src.main.mypage.present

interface PresentActivityView {
    fun onGetImageSuccess(response: String)
    fun onGetImageFail(message: String)
}