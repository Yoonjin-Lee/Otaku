package com.example.myapplication.src.main.mypage

import retrofit2.Response

interface MyPageFragmentView {
    fun onPostLogoutSuccess(response: String)
    fun onPostLogoutFail(message: String)
    fun onGetAdmissionSuccess(response: String)
    fun onGetAdmissionFail(message: String)
    fun onGetImageSuccess(response: String)
    fun onGetImageFail(message: String)
}