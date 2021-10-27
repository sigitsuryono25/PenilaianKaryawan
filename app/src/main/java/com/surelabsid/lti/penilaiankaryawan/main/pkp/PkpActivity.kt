package com.surelabsid.lti.penilaiankaryawan.main.pkp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.surelabsid.lti.penilaiankaryawan.R
import com.surelabsid.lti.penilaiankaryawan.databinding.ActivityPkpBinding
import com.surelabsid.lti.penilaiankaryawan.main.pkp.ui.PilihKaryawan

class PkpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPkpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPkpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.containerPkp, PilihKaryawan())
            .commit()

    }
}