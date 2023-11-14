package com.example.myapplication.src.main.search.map

import android.util.Log
import com.example.myapplication.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapService(val view: MapActivityView) {
    fun tryGetList(subjectId: Int){
        val mapRetrofitInterface = ApplicationClass.sRetrofit.create(MapRetrofitInterface::class.java)
        mapRetrofitInterface.getList(subjectId).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onGetListSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "data null")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onGetListFail(t.message as String)
            }
        })
    }

    fun tryGetLocation(key : String, query: String){
        val mapRetrofitInterface = ApplicationClass.kRetrofit.create(MapRetrofitInterface::class.java)
        mapRetrofitInterface.getLocation(key, query).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onGetLocationSuccess(response.body() as String)
                } else{
                    Log.d("Retrofit", "null 값 들어옴")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onGetLocationFail(t.message as String)
            }
        })
    }

    fun tryGetFirstLocation(key : String, query: String){
        val mapRetrofitInterface = ApplicationClass.kRetrofit.create(MapRetrofitInterface::class.java)
        mapRetrofitInterface.getLocation(key, query).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onGetFirstLocationSuccess(response.body() as String)
                } else{
                    Log.d("Retrofit", "null 값 들어옴")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onGetFirstLocationFail(t.message as String)
            }
        })
    }
}