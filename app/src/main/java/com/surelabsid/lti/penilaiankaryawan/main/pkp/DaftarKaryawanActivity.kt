package com.surelabsid.lti.penilaiankaryawan.main.pkp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.surelabsid.lti.penilaiankaryawan.databinding.ActivityDaftarKaryawanBinding
import com.surelabsid.lti.penilaiankaryawan.main.pkp.adapter.AdapterKaryawan
import com.surelabsid.lti.penilaiankaryawan.response.ResponseKaryawan
import es.dmoral.toasty.Toasty

class DaftarKaryawanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDaftarKaryawanBinding
    private lateinit var vm: PkpViewModel
    private var page = 0
    private lateinit var adapterKaryawan: AdapterKaryawan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarKaryawanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Daftar Karyawan"
            setDisplayHomeAsUpEnabled(true)
        }

        //instance for viewmodel
        vm = ViewModelProvider(this).get(PkpViewModel::class.java)

        val idJabatan = intent.getStringExtra("idJabatan")
        getData(idJabatan)

        vm.data.observe(this) {
            setToView(it)
        }

        vm.error.observe(this) {
            setError(it)
        }

        adapterKaryawan = AdapterKaryawan {
            val dataKar = Intent()
            dataKar.putExtra(DATA_KARYAWAN, it)
            setResult(Activity.RESULT_OK, dataKar)
            finish()
        }
        binding.listKaryawan.apply {
            adapter = adapterKaryawan
            layoutManager = LinearLayoutManager(
                this@DaftarKaryawanActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setError(throwable: Throwable) {
        Toasty.error(this, throwable.message.toString()).show()
    }

    private fun setToView(responseKaryawan: ResponseKaryawan) {
        responseKaryawan.dataKaryawan?.let { adapterKaryawan.addItem(it) }
    }

    private fun getData(idJabatan: String?) {
        vm.getListKaryawan(page, idJabatan)
    }

    companion object {
        const val DATA_KARYAWAN = "dataKaryawan"
    }
}