package com.surelabsid.lti.penilaiankaryawan

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.surelabsid.lti.penilaiankaryawan.databinding.ActivityMainBinding
import com.surelabsid.lti.penilaiankaryawan.main.akun.AkunFragment
import com.surelabsid.lti.penilaiankaryawan.main.home.HomeFragment
import com.surelabsid.lti.penilaiankaryawan.main.pkp.PkpActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Penilaian Kinerja Pegawai"
        }

        this.changeFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    this.changeFragment(HomeFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.akun -> {
                    this.changeFragment(AkunFragment())
                    return@setOnItemSelectedListener true
                }
            }
            return@setOnItemSelectedListener false
        }

        binding.pkp.setOnClickListener {
            Intent(this@MainActivity, PkpActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()

    }
}