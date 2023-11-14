package com.example.myapplication.src.main.mypage.admission

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AdmissionRetrofitInterface {
    @GET("/events/admission-ticket/{eventId}")
    fun getAdmission(@Path("eventId") eventId: Int): Call<String>

    @POST("/event-logs/code")
    fun postCode(
        @Query("eventId") eventId: Int,
        @Query("code") code: Int
    ) : Call<String>
}