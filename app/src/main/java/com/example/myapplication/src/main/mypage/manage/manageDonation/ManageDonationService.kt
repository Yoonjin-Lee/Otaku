package com.example.myapplication.src.main.mypage.manage.manageDonation

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.myapplication.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManageDonationService(val view: ManageDonationActivityView, val context: Context) {
    fun tryGetSupports(supportId: Int){
        val manageDonationRetrofitInterface = ApplicationClass.sRetrofit.create(ManageDonationRetrofitInterface::class.java)
        manageDonationRetrofitInterface.getSupports(supportId).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onGetSupportsSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "data null")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onGetSupportsFail(t.message as String)
            }
        })
    }

    fun tryPostSupport(supportId: Int){
        val manageDonationRetrofitInterface = ApplicationClass.sRetrofit.create(ManageDonationRetrofitInterface::class.java)
        manageDonationRetrofitInterface.postSupport(supportId).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onPostSupportSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "data null")
                    Toast.makeText(context, "실패했습니다", Toast.LENGTH_LONG)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onPostSupportFail(t.message as String)
            }
        })
    }
}