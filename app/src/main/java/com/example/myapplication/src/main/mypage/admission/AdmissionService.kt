package com.example.myapplication.src.main.mypage.admission

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.myapplication.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class AdmissionService(val view: AdmissionActivityView, val context: Context) {
    fun tryGetAdmission(eventId : Int){
        val admissionRetrofitInterface = ApplicationClass.sRetrofit.create(AdmissionRetrofitInterface::class.java)
        admissionRetrofitInterface.getAdmission(eventId).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onGetAdmissionSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "data null")
                }
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
                if (response.isSuccessful){
                    view.onPostCodeSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "data null")
                    Toast.makeText(context, "입장 코드가 다릅니다", Toast.LENGTH_LONG)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onPostCodeFail(t.message as String)
            }
        })
    }
}
