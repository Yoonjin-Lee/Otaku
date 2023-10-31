package com.example.myapplication.src.main.mypage.manage.manageParticipate

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ManageParticipateRetrofitInterface {
    @GET("/approvals")
    fun getUsers(
        @Query("eventId") eventId: Int
    ):Call<String>
    @POST("/approvals/{approvalId}")
    fun postUser(
        @Path(value = "approvalId") approvalId : Int
    ): Call<String>
}