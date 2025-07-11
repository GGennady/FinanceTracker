package com.example.financetracker.data.di.retrofit_interceptors

import com.example.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Adds a bearer token to every request. You can change it in local.properties by adding "API_KEY=your_key".
 */
class AuthInterceptor(): Interceptor {

    private val token = BuildConfig.API_KEY

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(newRequest)
    }
}