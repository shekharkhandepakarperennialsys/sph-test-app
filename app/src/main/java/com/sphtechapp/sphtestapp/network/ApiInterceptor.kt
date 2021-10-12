package com.sphtechapp.sphtestapp.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ApiInterceptor : Interceptor {
    private fun prepareInterceptorRequest(chain: Interceptor.Chain): Request {
        synchronized(this) {
            val builder = chain.request().newBuilder()
            return builder.build()
        }
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(prepareInterceptorRequest(chain))
}
