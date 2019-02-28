package com.alejofila.data.utils

interface ConnectivityManagerWrapper {

    fun isConnectedToNetwork(): Boolean

    fun getNetworkData(): NetworkData
}