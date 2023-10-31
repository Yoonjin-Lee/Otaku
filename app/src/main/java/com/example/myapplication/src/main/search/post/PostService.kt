package com.example.myapplication.src.main.search.post

import com.example.myapplication.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostService(val view: PostActivityView) {
    fun tryGetEvent(eventId: Int){
        val postRetrofitInterface = ApplicationClass.sRetrofit.create(com.example.myapplication.src.main.search.post.PostRetrofitInterface::class.java)
        postRetrofitInterface.getEvent(eventId).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                view.onGetEventSuccess(response.body() as String)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onGetEventFail(t.message as String)
            }
        })
    }
    fun tryPostWishList(eventId : Int){
        val postRetrofitInterface = ApplicationClass.sRetrofit.create(com.example.myapplication.src.main.search.post.PostRetrofitInterface::class.java)
        postRetrofitInterface.postWishList(eventId).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                view.onPostWishListSuccess(response.body() as String)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onPostWishListFail(t.message as String)
            }
        })
    }

    fun tryPostWishCancel(eventId: Int){
        val postRetrofitInterface = ApplicationClass.sRetrofit.create(com.example.myapplication.src.main.search.post.PostRetrofitInterface::class.java)
        postRetrofitInterface.postWishCancel(eventId).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                view.onPostWishCancelSuccess(response.body() as String)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onPostWishCancelFail(t.message as String)
            }
        })
    }
}