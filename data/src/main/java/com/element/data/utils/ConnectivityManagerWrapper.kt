package com.element.data.utils

interface ConnectivityManagerWrapper {

    fun isConnectedToNetwork(): Boolean

    fun getNetworkData(): NetworkData
}