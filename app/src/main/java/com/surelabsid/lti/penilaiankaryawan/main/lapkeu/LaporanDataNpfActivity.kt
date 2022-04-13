package com.surelabsid.lti.penilaiankaryawan.main.lapkeu

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.surelabsid.lti.penilaiankaryawan.R
import com.surelabsid.lti.penilaiankaryawan.databinding.ActivityLaporanDataNpfBinding
import com.surelabsid.lti.penilaiankaryawan.main.lapkeu.adapter.AdapterNpf
import com.surelabsid.lti.penilaiankaryawan.model.RequestLapKeu

class LaporanDataNpfActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLaporanDataNpfBinding
    private lateinit var lapKeuViewModel: LapKeuViewModel
    private lateinit var adapterNpf: AdapterNpf
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaporanDataNpfBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lapKeuViewModel = ViewModelProvider(this).get(LapKeuViewModel::class.java)

        supportActionBar?.apply {
            title = "Laporan NPF"
            setDisplayHomeAsUpEnabled(true)
        }

        adapterNpf = AdapterNpf {

        }
        binding.laporanNeraca.apply {
            adapter = adapterNpf
            layoutManager = LinearLayoutManager(this@LaporanDataNpfActivity)
        }
        val requestParam =
            intent.getParcelableExtra<RequestLapKeu>(LaporanDataNeracaActivity.REQUEST_PARAM)

        lapKeuViewModel.getDataNpf(requestParam)
        lapKeuViewModel.dataNpf.observe(this) {
            it.data01?.let { it1 -> adapterNpf.addItemNpf(it1) }
        }

        lapKeuViewModel.error.observe(this) {
            Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu?.findItem(R.id.app_bar_search)
        val searchView: SearchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapterNpf.searchItem(newText.toString())
                return true
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}