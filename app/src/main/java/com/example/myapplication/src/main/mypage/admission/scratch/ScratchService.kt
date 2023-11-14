package com.example.myapplication.src.main.mypage.admission.scratch

import android.util.Log
import com.example.myapplication.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScratchService(val view: ScratchActivityView) {
    fun tryGetEventImage(eventId: Int){
        val scratchRetrofitInterface = ApplicationClass.sRetrofit.create(ScratchRetrofitInterface::class.java)
        scratchRetrofitInterface.getEventImage(eventId).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onGetEventImageSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "data null")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onGetEventImageFail(t.message as String)
            }
        })
    }
}