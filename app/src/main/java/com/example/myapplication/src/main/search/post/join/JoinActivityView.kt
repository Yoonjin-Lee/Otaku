package com.example.myapplication.src.main.search.post.join

interface JoinActivityView {
    fun onPostEventSuccess(response: String)
    fun onPostEventFail(message: String)
}