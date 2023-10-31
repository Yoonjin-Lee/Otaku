package com.example.myapplication.src.main.mypage.present

import com.example.myapplication.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresentService(val view: PresentActivityView) {
    fun tryGetImage(eventId: Int){
        val presentRetrofitInterface = ApplicationClass.sRetrofit.create(PresentRetrofitInterface::class.java)
        presentRetrofitInterface.getImage(eventId).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                view.onGetImageSuccess(response.body() as String)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onGetImageFail(t.message as String)
            }
        })
    }
}