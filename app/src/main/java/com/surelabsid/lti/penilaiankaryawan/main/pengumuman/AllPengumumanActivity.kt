package com.surelabsid.lti.penilaiankaryawan.main.pengumuman

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.surelabsid.lti.penilaiankaryawan.databinding.ActivityAllPengumumanBinding
import com.surelabsid.lti.penilaiankaryawan.main.pengumuman.adapter.AdapterPengumuman
import com.surelabsid.lti.penilaiankaryawan.response.ResponsePengumuman

class AllPengumumanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllPengumumanBinding
    private lateinit var vm: PengumumanViewModel
    private lateinit var adapterPengumuman: AdapterPengumuman

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllPengumumanBinding.inflate(layoutInflater)
        vm = ViewModelProvider(this).get(PengumumanViewModel::class.java)

        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Pengumuman"
            setDisplayHomeAsUpEnabled(true)
        }

        adapterPengumuman = AdapterPengumuman {
            Intent(this, DetailPengumumanActivity::class.java).apply {
                putExtra("dataPengumuman", it)
                startActivity(this)
            }
        }
        binding.allPengumuman.apply {
            adapter = adapterPengumuman
            layoutManager = LinearLayoutManager(this@AllPengumumanActivity)
        }

        binding.shimmerPengumuman.startShimmer()

        vm.getLatestNews()
        vm.data.observe(this) {
            setToView(it)
        }
        vm.isLoading.observe(this) {
            if (it == View.GONE) {
                binding.allPengumuman.visibility = View.VISIBLE
                binding.shimmerPengumuman.stopShimmer()
                binding.shimmerPengumuman.visibility = View.GONE
            } else {
                binding.allPengumuman.visibility = View.GONE
                binding.shimmerPengumuman.startShimmer()
                binding.shimmerPengumuman.visibility = View.VISIBLE
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setToView(responsePengumuman: ResponsePengumuman) {
        responsePengumuman.dataPengumuman?.let { adapterPengumuman.addItem(it) }
    }
}