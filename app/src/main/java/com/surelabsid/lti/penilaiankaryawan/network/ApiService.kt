package com.surelabsid.lti.penilaiankaryawan.network

import com.surelabsid.lti.penilaiankaryawan.model.User
import com.surelabsid.lti.penilaiankaryawan.response.ResponsePengumumanDummy
import com.surelabsid.lti.penilaiankaryawan.response.ResponseUser
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("api/user/auth")
    suspend fun auth(@Body user: User): ResponseUser


    /**
     * testing data
     */
    @GET("get_latest_news")
    suspend fun getLatestNews() : ResponsePengumumanDummy

}
