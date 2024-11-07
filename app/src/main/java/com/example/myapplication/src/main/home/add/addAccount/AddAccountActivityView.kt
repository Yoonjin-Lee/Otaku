package com.example.myapplication.src.main.home.add.addAccount

interface AddAccountActivityView {
    fun onPostSupportSuccess(response: String)
    fun onPostSupportFail(message: String)
}