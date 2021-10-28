package com.surelabsid.lti.penilaiankaryawan.main.monitoring

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pixplicity.easyprefs.library.Prefs
import com.surelabsid.lti.penilaiankaryawan.databinding.ActivityMonitoringBinding
import com.surelabsid.lti.penilaiankaryawan.main.monitoring.adapter.AdapterDaftarPenilaian
import com.surelabsid.lti.penilaiankaryawan.response.ResponsePenilaian
import com.surelabsid.lti.penilaiankaryawan.utils.Constant
import es.dmoral.toasty.Toasty

class MonitoringActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMonitoringBinding
    private lateinit var vm: PenilaianViewModel
    private lateinit var adapterDaftarPenilaian: AdapterDaftarPenilaian

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMonitoringBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Monitoring Penilaian Karyawan"
            setDisplayHomeAsUpEnabled(true)
        }

        vm = ViewModelProvider(this).get(PenilaianViewModel::class.java)

        vm.getPenilaian(Prefs.getString(Constant.USERID))

        adapterDaftarPenilaian = AdapterDaftarPenilaian {
            Intent(this@MonitoringActivity, DetailPenilaianActivity::class.java).apply {
                putExtra("data_penilaian", it)
                startActivity(this)
            }
        }
        binding.daftarPenilaian.adapter = adapterDaftarPenilaian
        binding.daftarPenilaian.layoutManager = LinearLayoutManager(this)

        vm.data.observe(this) {
            setToView(it)
        }
        vm.error.observe(this) {
            setError(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setError(throwable: Throwable) {
        Toasty.error(this, throwable.message.toString()).show()
    }

    private fun setToView(responsePenilaian: ResponsePenilaian) {
        responsePenilaian.dataPenilaian?.let { adapterDaftarPenilaian.addData(it) }
    }
}