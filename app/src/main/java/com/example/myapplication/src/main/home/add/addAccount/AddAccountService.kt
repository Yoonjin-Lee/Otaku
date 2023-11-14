package com.example.myapplication.src.main.home.add.addAccount

import android.content.Context
import android.util.Log
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.src.main.home.add.addAccount.model.AccountData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddAccountService(val view: AddAccountActivityView, val context: Context) {
    fun tryPostSupport(eventId: Int, accountData: AccountData){
        val addAccountRetrofitInterface = ApplicationClass.sRetrofit.create(AddAccountRetrofitInterface::class.java)
        addAccountRetrofitInterface.postSupport(eventId, accountData).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    view.onPostSupportSuccess(response.body() as String)
                }else{
                    Log.d("Retrofit", "data null")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onPostSupportFail(t.message as String)
            }
        })
    }
}