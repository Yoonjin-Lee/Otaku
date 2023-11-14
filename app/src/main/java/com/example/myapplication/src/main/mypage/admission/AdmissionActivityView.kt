package com.example.myapplication.src.main.mypage.admission

import retrofit2.Call

interface AdmissionActivityView {
    fun onGetAdmissionSuccess(response : String)
    fun onGetAdmissionFail(message : String)
    fun onPostCodeSuccess(response: String)
    fun onPostCodeFail(message: String)
}