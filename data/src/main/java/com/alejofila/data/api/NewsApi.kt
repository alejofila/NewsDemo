package com.alejofila.data.api


import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET

interface NewsApi {
    @GET("spreadsheet/ccc?key=0Aqg9JQbnOwBwdEZFN2JKeldGZGFzUWVrNDBsczZxLUE&single=true&gid=0&output=csv")
    fun getNewsFile() : Single<ResponseBody>
}