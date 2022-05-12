package com.surelabsid.lti.penilaiankaryawan.main.test

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.surelabsid.lti.penilaiankaryawan.R
import com.surelabsid.lti.penilaiankaryawan.databinding.ActivityTestBinding
import com.surelabsid.lti.penilaiankaryawan.model.DataParam
import com.surelabsid.lti.penilaiankaryawan.model.RequestLapKeu
import com.surelabsid.lti.penilaiankaryawan.network.NetworkModule
import java.net.URLEncoder

class TestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.wv.settings.javaScriptEnabled = true
        val headers = hashMapOf<String, String>()
        headers.put("Device-Terminal", "d3nMas")

        val data01 = DataParam(
            tgl = "20210201",
            golac = "nrc",
            kdloc = "01"
        )
        val requestLapKeu = RequestLapKeu(
            request = "lapkeu",
            data01 = data01
        )
        val gson = Gson()
        val param = gson.toJson(requestLapKeu)
        val postData = "q=" + URLEncoder.encode(param)
//        binding.wv.loadUrl("${NetworkModule.DEBUG_URL}report/neraca")
        binding.wv.postUrl("${NetworkModule.DEBUG_URL}report/neraca", postData.toByteArray())


    }
}