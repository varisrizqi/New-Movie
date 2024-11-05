package com.tipiz.core.data.network.retrofit

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.tipiz.core.BuildConfig.BASE_URL
import com.tipiz.core.BuildConfig.Bearer
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class NetworkClient(
    val chuckerInterceptor: ChuckerInterceptor
) {

    inner class AuthInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()

            val newRequest = request.newBuilder().addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer $Bearer").build()

            return chain.proceed(newRequest)
        }

    }

    inline fun <reified I> create(): I {


        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(chuckerInterceptor)
            .addInterceptor(AuthInterceptor())
            .connectTimeout(timeout = 120, TimeUnit.SECONDS)
            .readTimeout(timeout = 120, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(I::class.java)
    }
}