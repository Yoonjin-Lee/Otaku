package com.example.myapplication.src.main.search.result

interface ResultActivityView {
    fun onGetSubjectSuccess(response: String)
    fun onGetSubjectFail(message: String)
    fun onGetAllSubjectsSuccess(response: String)
    fun onGetAllSubjectsFail(message: String)
}