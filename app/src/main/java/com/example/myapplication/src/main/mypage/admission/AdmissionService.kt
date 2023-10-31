package com.example.myapplication.src.main.mypage.admission

import com.example.myapplication.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class AdmissionService(val view: AdmissionActivityView) {
    fun tryGetAdmission(eventId : Int){
        val admissionRetrofitInterface = ApplicationClass.sRetrofit.create(AdmissionRetrofitInterface::class.java)
        admissionRetrofitInterface.getAdmission(eventId).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                view.onGetAdmissionSuccess(response.body() as String)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onGetAdmissionFail(t.message as String)
            }
        })
    }
    fun tryPostCode(eventId: Int, code: Int){
        val admissionRetrofitInterface = ApplicationClass.sRetrofit.create(AdmissionRetrofitInterface::class.java)
        admissionRetrofitInterface.postCode(eventId, code).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                view.onPostCodeSuccess(response.body() as String)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onPostCodeFail(t.message as String)
            }
        })
    }
}
