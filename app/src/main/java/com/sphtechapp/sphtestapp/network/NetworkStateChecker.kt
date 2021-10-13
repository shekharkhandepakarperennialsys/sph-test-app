package com.sphtechapp.sphtestapp.network

interface NetworkStateChecker {
    fun isNetworkAvailable(): Boolean
}