package com.example.financetracker.di.app_component

import com.example.financetracker.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Adds a bearer token to every request. You can change it here to your token.
 */
class AuthInterceptor: Interceptor {
    private val token = BuildConfig.API_KEY

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(newRequest)
    }
}