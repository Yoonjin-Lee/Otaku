package com.example.myapplication.src.main.mypage

import retrofit2.Response

interface MyPageFragmentView {
    fun onPostLogoutSuccess(response: Response<String>)
    fun onPostLogoutFail(message: String)
}