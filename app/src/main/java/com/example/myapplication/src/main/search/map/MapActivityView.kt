package com.example.myapplication.src.main.search.map

interface MapActivityView {
    fun onGetListSuccess(response: String)
    fun onGetListFail(message: String)
    fun onGetLocationSuccess(response: String)
    fun onGetLocationFail(message: String)
    fun onGetFirstLocationSuccess(response: String)
    fun onGetFirstLocationFail(message: String)
}