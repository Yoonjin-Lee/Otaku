package com.example.myapplication.src.main.home

import android.content.Context
import android.util.Log
import com.example.myapplication.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class HomeService(val view : HomeFragmentView, val context: Context) {
    fun tryGetEvents(){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.getEvents().enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onGetEventsSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "$response")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onGetEventsFail(t.message as String)
            }
        })
    }
}