package com.example.myapplication.src.login

interface LoginActivityView {
    fun onPostSignUpSuccess(response: String)
    fun onPostSignUpFail(message: String)
}