package com.example.myapplication.src.main.search.post.donate

import android.util.Log
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.src.main.search.post.donate.model.SupportData
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DonateService(val view: DonateActivityView) {
    fun tryGetSupport(supportId: Int){
        val donateRetrofitInterface = ApplicationClass.sRetrofit.create(DonateRetrofitInterface::class.java)
        donateRetrofitInterface.getSupport(supportId).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onGetSupportSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "data null")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onGetSupportFail(t.message as String)
            }
        })
    }

    fun tryPostSupport(supportId: Int, image: MultipartBody.Part, supportData: SupportData){
        val donateRetrofitInterface = ApplicationClass.sRetrofit.create(DonateRetrofitInterface::class.java)
        donateRetrofitInterface.postSupport(supportId, image, supportData).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onPostSupportSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "data null")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onPostSupportFail(t.message as String)
            }
        })
    }
}