package com.example.myapplication.src.main.mypage.certificate

interface CertificateActivityView {
    fun onPostHostSuccess(response: String)
    fun onPostHostFail(message: String)
}