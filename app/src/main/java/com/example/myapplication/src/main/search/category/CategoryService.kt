package com.example.myapplication.src.main.search.category

import android.util.Log
import com.example.myapplication.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryService(val view: CategoryActivityView) {
    fun tryGetCategory(title : String){
        val categoryRetrofitInterface = ApplicationClass.sRetrofit.create(CategoryRetrofitInterface::class.java)
        categoryRetrofitInterface.getCategory(0, 10, title, 0).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d("Retrofit", "${response.body()}")
                if (response.body() != null){
                    view.onGetCategorySuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "response가 null")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onGetCategoryFail(t.message as String)
            }
        })
    }
}