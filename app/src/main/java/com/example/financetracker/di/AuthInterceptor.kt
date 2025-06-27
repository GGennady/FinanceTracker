package com.example.financetracker.di

import com.example.financetracker.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Adds a bearer token to every request. You can change it here to your token.
 */
class AuthInterceptor @Inject constructor(): Interceptor {
    private val token = BuildConfig.API_KEY

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(newRequest)
    }
}