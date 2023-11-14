package com.example.myapplication.src.main.search.post.join

import com.example.myapplication.src.main.search.post.join.model.JoinData
import com.example.myapplication.src.main.search.post.join.model.RequestData
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface JoinRetrofitInterface {
    @POST("/event-logs/pre-auth")
    fun postEvent(
        @Query("eventId") eventId: Int,
        @Body request: JoinData
    ): Call<String>
}