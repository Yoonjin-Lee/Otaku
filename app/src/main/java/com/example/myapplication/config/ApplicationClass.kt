package com.example.myapplication.config

import android.app.Application
import android.content.SharedPreferences
import com.google.gson.*
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
import java.lang.reflect.Type
import java.net.CookieManager
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class ApplicationClass : Application() {
    val API_URL = "http://3.37.186.131:8080"

    companion object{
        lateinit var sSharedPreferences : SharedPreferences
        lateinit var sRetrofit: Retrofit
        lateinit var kRetrofit: Retrofit
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

        val kClient : OkHttpClient = OkHttpClient.Builder()
            .cookieJar(JavaNetCookieJar(CookieManager()))
            .addInterceptor(interceptor)
            .build()

        val gson = GsonBuilder()
            .setLenient() // Gson은 JSON에 대해 엄격함. 해당 옵션으로 parser가 허용하는 항목에 대해 자유로움.
            .registerTypeAdapter(LocalDate::class.java, LocalDateSerializer()) // 날짜관련된
            .setPrettyPrinting()
            .create()

        sRetrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

        kRetrofit = Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(kClient)
            .build()
    }

}
class LocalDateSerializer : JsonSerializer<LocalDate?> {
    override fun serialize(
        localDate: LocalDate?,
        srcType: Type,
        context: JsonSerializationContext
    ): JsonElement {
        return JsonPrimitive(formatter.format(localDate))
    }

    companion object {
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    }
}
