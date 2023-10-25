package com.example.myapplication.src.main.mypage

import com.example.myapplication.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageService(val view: MyPageFragmentView) {
    fun tryPostLogout(){
        val myPageRetrofitInterface = ApplicationClass.sRetrofit.create(MyPageRetrofitInterface::class.java)
        myPageRetrofitInterface.postLogout().enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                view.onPostLogoutSuccess(response)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onPostLogoutFail(t.message ?: "통신 오류")
            }
        })
    }
}