package com.example.myapplication.config

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostAdapterService(val view: PostDVAdapterView) {
    fun tryPostWishList(eventId : Int){
        val postAdapterRetrofitInterface = ApplicationClass.sRetrofit.create(PostAdapterRetrofitInterface::class.java)
        postAdapterRetrofitInterface.postWishList(eventId).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                view.onPostWishListSuccess(response.body() as String)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onPostWishListFail(t.message as String)
            }
        })
    }

    fun tryPostWishCancel(eventId: Int){
        val postAdapterRetrofitInterface = ApplicationClass.sRetrofit.create(PostAdapterRetrofitInterface::class.java)
        postAdapterRetrofitInterface.postWishCancel(eventId).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                view.onPostWishCancelSuccess(response.body() as String)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onPostWishCancelFail(t.message as String)
            }
        })
    }
}