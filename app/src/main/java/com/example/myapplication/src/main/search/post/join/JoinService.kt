package com.example.myapplication.src.main.search.post.join

import android.util.Log
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.src.main.search.post.join.model.JoinData
import com.example.myapplication.src.main.search.post.join.model.RequestData
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JoinService(val view: JoinActivityView) {
    fun tryPostEvent(eventId: Int, jsonObject: JoinData){
        val joinRetrofitInterface = ApplicationClass.sRetrofit.create(JoinRetrofitInterface::class.java)
        joinRetrofitInterface.postEvent(eventId, jsonObject).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onPostEventSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "data null")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onPostEventFail(t.message as String)
            }
        })
    }
}