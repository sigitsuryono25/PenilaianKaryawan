package com.surelabsid.lti.penilaiankaryawan.network

import com.surelabsid.lti.penilaiankaryawan.model.PenilaianSend
import com.surelabsid.lti.penilaiankaryawan.model.User
import com.surelabsid.lti.penilaiankaryawan.response.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("api/user/auth")
    suspend fun auth(@Body user: User): ResponseUser

    @GET("api/user/list")
    suspend fun getKaryawan(
        @Query("p") page: Int = 0,
        @Query("id_jabatan") idJabatan: String?
    ): ResponseKaryawan


    @GET("api/user/jabatan")
    suspend fun getJabatan(@Query("level") level: String?): ResponseJabatan

    @GET("api/penilaian/param")
    suspend fun getParam(@Query("id_jabatan") idJabatan: String?): ResponseParams

    @POST("api/penilaian/received")
    suspend fun sendPenilaian(@Body penilaianSend: PenilaianSend): GeneralResponse


    @GET("api/penilaian/get-penilaian")
    suspend fun getPenilaian(@Query("dinilai_oleh") dinilaiOleh: String?): ResponsePenilaian

    @GET("api/penilaian/get-penilaian-by-user")
    suspend fun getPenilaianByUser(@Query("id_karyawan") idKaryawan: String?): ResponsePenilaian

    @GET("api/pengumuman/list")
    suspend fun getPengumuman(): ResponsePengumuman

    @GET("api/settings")
    suspend fun getSettings(): ResponseSettings

    /**
     * testing data
     */
    @GET("get_latest_news")
    suspend fun getLatestNews(): ResponsePengumumanDummy

}
