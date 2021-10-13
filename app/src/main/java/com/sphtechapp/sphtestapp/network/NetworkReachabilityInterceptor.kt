package com.sphtechapp.sphtestapp.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkReachabilityInterceptor(private val networkStateChecker: NetworkStateChecker) :
    Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkStateChecker.isNetworkAvailable()) {
            throw NetworkAvailabilityException("Network not available")
        }
        val request = chain.request()
        return chain.proceed(request)
    }

}

class NetworkAvailabilityException(message: String) : Exception(message)