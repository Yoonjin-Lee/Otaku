package com.example.myapplication.src.main.search

import android.util.Log
import com.example.myapplication.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchService(val view: SearchFragmentView) {
    fun tryGetAllSubjects(){
        val searchRetrofitInterface = ApplicationClass.sRetrofit.create(SearchRetrofitInterface::class.java)
        searchRetrofitInterface.getAllSubjects().enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onGetAllSubjectsSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "data null")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onGetAllSubjectsFail(t.message as String)
            }
        })
    }
}