package com.surelabsid.lti.penilaiankaryawan.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
//    const val BASE_URL = "http://192.168.18.114/codeigniter/PenilaianKaryawanBMT/"
    const val BASE_URL = "http://bmt-bima.server4111.com/"
    const val BASE_URL_LTI = "https://lauwba.com/webservices/"


    const val FIREBASE_URL = "https://fcm.googleapis.com/"

    private fun getOkHttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL + "index.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttp())
            .build()
    }

    private fun getRetrofitFCM(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(FIREBASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttp())
            .build()
    }
    private fun getRetrofitLTI(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_LTI)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttp())
            .build()
    }

    fun getFcmService(): ApiService {
        return getRetrofitFCM().create(ApiService::class.java)
    }
    fun getFcmServiceLTI(): ApiService {
        return getRetrofitLTI().create(ApiService::class.java)
    }

    fun getService(): ApiService {
        return getRetrofit().create(ApiService::class.java)
    }
}