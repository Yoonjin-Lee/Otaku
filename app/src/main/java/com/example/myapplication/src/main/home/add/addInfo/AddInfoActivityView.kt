package com.example.myapplication.src.main.home.add.addInfo

interface AddInfoActivityView {
    fun onGetSubjectsSuccess(response: String)
    fun onGetSubjectsFail(message: String)
    fun onPostSubjectSuccess(response: String)
    fun onPostSubjectFail(message: String)
}