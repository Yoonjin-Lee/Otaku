package com.example.myapplication.src.main.search.category

interface CategoryActivityView {
    fun onGetCategorySuccess(response : String)
    fun onGetCategoryFail(message : String)
}