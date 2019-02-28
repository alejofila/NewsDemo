package com.alejofila.data.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager

class ConnectivityManagerWrapperImpl(context: Context) : ConnectivityManagerWrapper {

    private val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @SuppressLint("MissingPermission")
    override fun isConnectedToNetwork(): Boolean {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    @SuppressLint("MissingPermission")
    override fun getNetworkData(): NetworkData {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        val hasInternetConnection = activeNetworkInfo != null && activeNetworkInfo.isConnected
        val isMobileConnection = activeNetworkInfo != null && activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE
        return NetworkData(hasInternetConnection, isMobileConnection)
    }
}