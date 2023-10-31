package com.example.myapplication.src.main.mypage.manage.manageParticipate

import com.example.myapplication.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManageParticipateService(val view: ManageParticipateActivityView) {
    fun tryGetUsers(eventId: Int){
        val manageParticipateRetrofitInterface = ApplicationClass.sRetrofit.create(ManageParticipateRetrofitInterface::class.java)
        manageParticipateRetrofitInterface.getUsers(eventId).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                view.onGetUsersSuccess(response.body() as String)
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
                view.onPostUserSuccess(response.body() as String)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onPostUserFail(t.message as String)
            }
        })
    }
}