package com.example.myapplication.src.main.mypage

import android.content.Context
import android.util.Log
import com.example.myapplication.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MyPageService(val view: MyPageFragmentView, val context: Context) {
    fun tryPostLogout() {
        val myPageRetrofitInterface =
            ApplicationClass.sRetrofit.create(MyPageRetrofitInterface::class.java)
        myPageRetrofitInterface.postLogout().enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onPostLogoutSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "data null")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onPostLogoutFail(t.message ?: "통신 오류")
            }
        })
    }

    fun tryGetAdmission() {
        val myPageRetrofitInterface =
            ApplicationClass.sRetrofit.create(MyPageRetrofitInterface::class.java)
        myPageRetrofitInterface.getAdmission().enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onGetAdmissionSuccess(response.body() as String)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onGetAdmissionFail(t.message as String)
            }
        })
    }

    fun tryGetImage() {
        val myPageRetrofitInterface =
            ApplicationClass.sRetrofit.create(MyPageRetrofitInterface::class.java)
        myPageRetrofitInterface.getImage().enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onGetImageSuccess(response.body() as String)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onGetImageSuccess(t.message as String)
            }
        })
    }
}