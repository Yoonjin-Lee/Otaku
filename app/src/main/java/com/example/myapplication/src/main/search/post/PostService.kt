package com.example.myapplication.src.main.search.post

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.src.main.search.SearchRetrofitInterface
import com.example.myapplication.src.main.search.map.MapRetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostService(val view: PostActivityView, val context: Context) {
    fun tryGetEvent(eventId: Int){
        val postRetrofitInterface = ApplicationClass.sRetrofit.create(com.example.myapplication.src.main.search.post.PostRetrofitInterface::class.java)
        postRetrofitInterface.getEvent(eventId).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onGetEventSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "no data")
                }

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
                if (response.isSuccessful){
                    view.onPostWishListSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "data null")
                }
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
                if (response.isSuccessful){
                    view.onPostWishCancelSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "data null")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onPostWishCancelFail(t.message as String)
            }
        })
    }

    fun tryPostReport(eventId: Int){
        val postRetrofitInterface = ApplicationClass.sRetrofit.create(com.example.myapplication.src.main.search.post.PostRetrofitInterface::class.java)
        postRetrofitInterface.postReport(eventId).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onPostReportSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "data null")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onPostReportFail(t.message as String)
            }
        })
    }

    fun tryPostEventPublic(eventId: Int){
        val postRetrofitInterface = ApplicationClass.sRetrofit.create(com.example.myapplication.src.main.search.post.PostRetrofitInterface::class.java)
        postRetrofitInterface.postEventPublic(eventId).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    view.onPostEventPublicSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "no data")
                    Toast.makeText(context, "이미 참여한 이벤트입니다", Toast.LENGTH_LONG)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onPostEventPublicFail(t.message as String)
            }
        })
    }

    fun tryGetAllSubjects(){
        val searchRetrofitInterface = ApplicationClass.sRetrofit.create(SearchRetrofitInterface::class.java)
        searchRetrofitInterface.getAllSubjects().enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onGetAllSubjectsSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "data null")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onGetAllSubjectsFail(t.message as String)
            }
        })
    }
}