package com.example.myapplication.src.main.mypage.certificate

import android.util.Log
import com.example.myapplication.config.ApplicationClass
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CertificateService(val view: CertificateActivityView) {
    fun tryPostHost(multipart: MultipartBody.Part){
        val certificateRetrofitInterface = ApplicationClass.sRetrofit.create(CertificateRetrofitInterface::class.java)
        certificateRetrofitInterface.postHost(multipart).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onPostHostSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "response 없음")
                    Log.d("Retrofit", "${ApplicationClass.sSharedPreferences.getString("accessToken", "")}")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onPostHostFail(t.message ?: "통신 오류")
            }
        })
    }
}