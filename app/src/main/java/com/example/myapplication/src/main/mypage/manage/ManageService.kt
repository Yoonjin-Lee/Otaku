package com.example.myapplication.src.main.mypage.manage

import com.example.myapplication.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManageService(val view: ManageActivityView) {
    fun tryGetHostEvents(){
        val manageRetrofitInterface = ApplicationClass.sRetrofit.create(ManageRetrofitInterface::class.java)
        manageRetrofitInterface.getHostEvents().enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                view.onGetHostEventsSuccess(response.body() as String)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onGetHostEventsFail(t.message as String)
            }
        })
    }
}