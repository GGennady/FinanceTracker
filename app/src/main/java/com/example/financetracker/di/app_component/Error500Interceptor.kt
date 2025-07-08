package com.example.financetracker.di.app_component

import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

/**
 * Only for tests! to execute - add ".addInterceptor(Error500Interceptor())" inside provideOkHttpClient function.
 */
class Error500Interceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return Response.Builder()
            .request(chain.request())
            .protocol(Protocol.HTTP_1_1)
            .code(500)
            .message("Simulated Server Error")
            .body("Internal Error".toResponseBody())
            .build()
    }
}