package com.sphtechapp.sphtestapp.network

import retrofit2.Response

class ErrorHandler {
    companion object {
        fun getError(response: Response<out Any>): String {
            val errorBody = response.errorBody()?.string()
            return errorBody ?: "something went wrong"
        }
    }
}