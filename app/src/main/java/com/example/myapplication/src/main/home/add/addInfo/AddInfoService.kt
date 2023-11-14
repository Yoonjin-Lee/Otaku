package com.example.myapplication.src.main.home.add.addInfo

import android.content.Context
import android.util.Log
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.src.main.home.add.addInfo.model.CategoryData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddInfoService(val view: AddInfoActivityView, val context: Context) {
    fun tryGetSubjects(){
        val addInfoRetrofitInterface = ApplicationClass.sRetrofit.create(AddInfoRetrofitInterface::class.java)
        addInfoRetrofitInterface.getSubjects().enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onGetSubjectsSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "data null")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onGetSubjectsFail(t.message as String)
            }
        })
    }

    fun tryPostSubject(categoryData: CategoryData){
        val addInfoRetrofitInterface = ApplicationClass.sRetrofit.create(AddInfoRetrofitInterface::class.java)
        addInfoRetrofitInterface.postSubject(categoryData).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onPostSubjectSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "data null")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onPostSubjectFail(t.message as String)
            }
        })
    }
}