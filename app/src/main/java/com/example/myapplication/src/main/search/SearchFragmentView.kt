package com.example.myapplication.src.main.search

interface SearchFragmentView {
    fun onGetAllSubjectsSuccess(response: String)
    fun onGetAllSubjectsFail(message: String)
}