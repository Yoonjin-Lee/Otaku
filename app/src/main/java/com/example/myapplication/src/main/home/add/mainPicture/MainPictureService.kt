package com.example.myapplication.src.main.home.add.mainPicture

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.src.main.home.add.mainPicture.model.Request
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPictureService(val view: MainPictureActivityView, val context: Context) {
    fun tryPostEvent(
        featuredImageFile: MultipartBody.Part,
        perksImageFile: MultipartBody.Part,
        request: Request
    ){
        val mainPictureRetrofitInterface = ApplicationClass.sRetrofit.create(MainPictureRetrofitInterface::class.java)
        mainPictureRetrofitInterface.postEvent(
            featuredImageFile,
            perksImageFile,
            request
        ).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onPostEventSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "no request")

                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onPostEventFail(t.message as String)
            }
        })
    }
}