package com.example.myapplication.src.main.heart

import com.example.myapplication.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeartService(val view: HeartFragmentView) {
    fun tryGetWishEvents(){
        val heartRetrofitInterface = ApplicationClass.sRetrofit.create(HeartRetrofitInterface::class.java)
        heartRetrofitInterface.getWishEvents(true).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                view.onGetWishEventsSuccess(response.body() as String)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onGetWishEventsFail(t.message as String)
            }
        })
    }
}