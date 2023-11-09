package com.example.myapplication.src.main.mypage.manage.manageParticipate

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.myapplication.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManageParticipateService(val view: ManageParticipateActivityView, val context: Context) {
    fun tryGetUsers(eventId: Int){
        val manageParticipateRetrofitInterface = ApplicationClass.sRetrofit.create(ManageParticipateRetrofitInterface::class.java)
        manageParticipateRetrofitInterface.getUsers(eventId).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onGetUsersSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "data null")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onGetUsersFail(t.message as String)
            }
        })
    }
    fun tryPostUser(approvalId: Int){
        val manageParticipateRetrofitInterface = ApplicationClass.sRetrofit.create(ManageParticipateRetrofitInterface::class.java)
        manageParticipateRetrofitInterface.postUser(approvalId).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onPostUserSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "data null")
                    Toast.makeText(context, "실패했습니다", Toast.LENGTH_LONG)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onPostUserFail(t.message as String)
            }
        })
    }
}