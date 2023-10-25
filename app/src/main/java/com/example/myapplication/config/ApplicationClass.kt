package com.example.myapplication.config

import android.app.Application
import android.content.SharedPreferences
import com.kakao.sdk.common.KakaoSdk
import okhttp3.Interceptor
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.IOException
import java.net.CookieManager

class ApplicationClass : Application() {
    val API_URL = "http://3.37.186.131:8080"

    companion object{
        lateinit var sSharedPreferences : SharedPreferences
        lateinit var sRetrofit: Retrofit
    }

    override fun onCreate() {
        super.onCreate()
        sSharedPreferences = applicationContext.getSharedPreferences("OTAKU_APP", MODE_PRIVATE)
        initRetrofitInstance()
        KakaoSdk.init(this, "8fdfc36b147e600bf5e4c7385032ff64")
    }

    private fun initRetrofitInstance() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        class HeaderInterceptor constructor(private val token: String) : Interceptor {

            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val token = "Bearer $token"
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", token)
                    .build()
                return chain.proceed(newRequest)
            }
        }

        val client : OkHttpClient = OkHttpClient.Builder()
            .cookieJar(JavaNetCookieJar(CookieManager()))
            .addInterceptor(interceptor)
            .addInterceptor(HeaderInterceptor(sSharedPreferences.getString("accessToken", "") as String))
            .build()

        sRetrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}