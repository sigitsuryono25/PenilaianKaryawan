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
import com.surelabsid.lti.penilaiankaryawan.databinding.ActivityLaporanDataNeracaBinding
import com.surelabsid.lti.penilaiankaryawan.main.lapkeu.adapter.AdapterNeraca
import com.surelabsid.lti.penilaiankaryawan.model.RequestLapKeu

class LaporanDataNeracaActivity : AppCompatActivity() {
    private lateinit var lapKeuViewModel: LapKeuViewModel
    private lateinit var binding: ActivityLaporanDataNeracaBinding
    private lateinit var adapterNeraca: AdapterNeraca
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaporanDataNeracaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Laporan Data Neraca"
            setDisplayHomeAsUpEnabled(true)
        }

        lapKeuViewModel = ViewModelProvider(this).get(LapKeuViewModel::class.java)

        adapterNeraca = AdapterNeraca()
        binding.laporanNeraca.apply {
            adapter = adapterNeraca
            layoutManager = LinearLayoutManager(this@LaporanDataNeracaActivity)
        }
        val requestParam = intent.getParcelableExtra<RequestLapKeu>(REQUEST_PARAM)

        lapKeuViewModel.getDataNeraca(requestParam)
        lapKeuViewModel.dataNeraca.observe(this) {
            it.data01?.let { it1 -> adapterNeraca.addItem(it1) }
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
                adapterNeraca.searchItem(newText.toString())
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

    companion object {
        const val REQUEST_PARAM = "requesParam"
    }
}