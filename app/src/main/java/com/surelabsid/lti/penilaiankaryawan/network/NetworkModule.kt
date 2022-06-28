package com.surelabsid.lti.penilaiankaryawan.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    //ganti alamat ini ke alamat/domain dimana bagian admin berada
    const val BASE_URL = "http://bmt-bima.server4111.com/"

    //ini base URL yang dipakai untuk laporan keuangan
    const val BASEURLLAPORAN = "http://202.43.164.234:12310/"


    private fun getOkHttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    private fun getOkHttpLapKeu(): OkHttpClient {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor {
                val req = it.request().newBuilder()
                    .addHeader("Device-Terminal", "d3nMas")
                    .build()
                return@addInterceptor it.proceed(req)
            }
            .build()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL + "index.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttp())
            .build()
    }

    private fun getRetrofitLaporanKeu(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASEURLLAPORAN)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpLapKeu())
            .build()
    }

    fun getServiceLapKeu(): ApiService {
        return getRetrofitLaporanKeu().create(ApiService::class.java)
    }

    fun getService(): ApiService {
        return getRetrofit().create(ApiService::class.java)
    }
}