package com.example.myapplication.src.main.home.add.addAccount

import com.example.myapplication.src.main.home.add.addAccount.model.AccountData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface AddAccountRetrofitInterface {
    @POST("/supports")
    fun postSupport(
        @Query("eventId") eventId : Int,
        @Body accountData: AccountData
    ):Call<String>
}