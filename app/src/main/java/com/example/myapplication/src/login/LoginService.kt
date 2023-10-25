package com.example.myapplication.src.login

import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.src.login.models.PostSignUpRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(val view: LoginActivityView) {
    fun tryPostSignUp(postSignUpRequest: PostSignUpRequest){
        val loginRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        loginRetrofitInterface.postSignUp(postSignUpRequest).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                view.onPostSignUpSuccess(response.body() as String)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onPostSignUpFail(t.message ?: "통신 오류")
            }
        })
    }
}