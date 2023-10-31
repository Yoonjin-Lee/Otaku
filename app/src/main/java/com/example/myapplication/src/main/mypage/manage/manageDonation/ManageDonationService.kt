package com.example.myapplication.src.main.mypage.manage.manageDonation

import com.example.myapplication.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManageDonationService(val view: ManageDonationActivityView) {
    fun tryGetSupports(supportId: Int){
        val manageDonationRetrofitInterface = ApplicationClass.sRetrofit.create(ManageDonationRetrofitInterface::class.java)
        manageDonationRetrofitInterface.getSupports(supportId).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                view.onGetSupportsSuccess(response.body() as String)
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
                view.onPostSupportSuccess(response.body() as String)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onPostSupportFail(t.message as String)
            }
        })
    }
}