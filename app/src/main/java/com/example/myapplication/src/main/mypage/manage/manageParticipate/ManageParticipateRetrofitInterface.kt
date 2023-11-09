package com.example.myapplication.src.main.mypage.manage.manageParticipate

import retrofit2.Call
import retrofit2.http.*

interface ManageParticipateRetrofitInterface {
    @GET("/approvals")
    fun getUsers(
        @Query("eventId") eventId: Int
    ):Call<String>
    @PUT("/approvals/{approvalId}")
    fun postUser(
        @Path(value = "approvalId") approvalId : Int
    ): Call<String>
}