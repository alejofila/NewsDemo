package com.alejofila.data.utils

import io.reactivex.Single

interface NetworkUtils {

    fun isConnectedToInternet(): Single<Boolean>

    fun activeNetworkData(): Single<NetworkData>
}