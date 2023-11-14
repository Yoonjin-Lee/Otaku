package com.example.myapplication.src.main.home

interface HomeFragmentView {
    fun onGetEventsSuccess(response : String)
    fun onGetEventsFail(message : String)
}