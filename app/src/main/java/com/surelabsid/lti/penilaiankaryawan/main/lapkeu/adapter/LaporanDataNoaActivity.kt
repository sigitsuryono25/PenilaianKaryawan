package com.surelabsid.lti.penilaiankaryawan.main.lapkeu.adapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.surelabsid.lti.penilaiankaryawan.databinding.ActivityLaporanDataNoaBinding
import com.surelabsid.lti.penilaiankaryawan.main.lapkeu.LapKeuViewModel

class LaporanDataNoaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLaporanDataNoaBinding
    private lateinit var lapKeuViewModel: LapKeuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaporanDataNoaBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}