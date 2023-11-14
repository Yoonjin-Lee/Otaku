package com.example.myapplication.src.main.mypage.manage

import android.util.Log
import com.example.myapplication.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManageService(val view: ManageActivityView) {
    fun tryGetHostEvents(){
        val manageRetrofitInterface = ApplicationClass.sRetrofit.create(ManageRetrofitInterface::class.java)
        manageRetrofitInterface.getHostEvents().enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onGetHostEventsSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "data_null")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onGetHostEventsFail(t.message as String)
            }
        })
    }
}