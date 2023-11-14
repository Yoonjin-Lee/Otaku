package com.example.myapplication.src.main.search.result

import android.util.Log
import com.example.myapplication.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultService(val view : ResultActivityView) {
    fun tryGetSubject(string: String){
        val resultRetrofitInterface = ApplicationClass.sRetrofit.create(ResultRetrofitInterface::class.java)
        resultRetrofitInterface.getSubject(string).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onGetSubjectSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "data null")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onGetSubjectFail(t.message as String)
            }
        })
    }

    fun tryGetAllSubjects(){
        val resultRetrofitInterface = ApplicationClass.sRetrofit.create(ResultRetrofitInterface::class.java)
        resultRetrofitInterface.getAllSubjects().enqueue(object : Callback<String>{
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