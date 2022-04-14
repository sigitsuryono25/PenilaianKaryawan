package com.surelabsid.lti.penilaiankaryawan.main.lapkeu

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.surelabsid.lti.penilaiankaryawan.databinding.ActivityLapKeuBinding
import com.surelabsid.lti.penilaiankaryawan.model.DataParam
import com.surelabsid.lti.penilaiankaryawan.model.RequestLapKeu
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import java.util.*

class LapKeuActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private lateinit var binding: ActivityLapKeuBinding
    private lateinit var now: Calendar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLapKeuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        now = Calendar.getInstance()

        supportActionBar?.apply {
            title = "Laporan Keuangan"
            setDisplayHomeAsUpEnabled(true)
        }

        val dpd = DatePickerDialog.newInstance(
            this,
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH)
        )

        binding.neraca.setOnClickListener {
            dpd.show(supportFragmentManager, "neraca")
        }

        binding.rugiLaba.setOnClickListener {
            dpd.show(supportFragmentManager, "laba-rugi")
        }

        binding.npf.setOnClickListener {
            dpd.show(supportFragmentManager, "npf")
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val dat = String.format("%d%02d%02d", year, monthOfYear + 1, dayOfMonth)
        if (view?.tag == "neraca") {
            val data01 = DataParam(
                tgl = dat,
                golac = "nrc",
                kdloc = "01"
            )
            val requestLapKeu = RequestLapKeu(
                request = "lapkeu",
                data01 = data01
            )
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Tampilkan Laporan dalam bentuk:")
                .setPositiveButton("Table") { d, i ->
                    Intent(this, LaporanTableViewActivity::class.java).apply {
                        putExtra(LaporanTableViewActivity.REQ_LAP_KEU, requestLapKeu)
                        putExtra(LaporanTableViewActivity.URL_REQ, "report/neraca")
                        putExtra(LaporanTableViewActivity.TITLE_REQ, "Laporan Neraca")
                        startActivity(this)
                    }
                }
                .setNegativeButton("List/Daftar") { d, i ->
                    Intent(this, LaporanDataNeracaActivity::class.java).apply {
                        putExtra(LaporanDataNeracaActivity.REQUEST_PARAM, requestLapKeu)
                        startActivity(this)
                    }
                }.create().show()
        } else if (view?.tag == "laba-rugi") {
            val data01 = DataParam(
                tgl = dat,
                golac = "lr",
                kdloc = "01"
            )
            val requestLapKeu = RequestLapKeu(
                request = "lapkeu",
                data01 = data01
            )

            AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Tampilkan Laporan dalam bentuk:")
                .setPositiveButton("Table") { d, i ->
                    Intent(this, LaporanTableViewActivity::class.java).apply {
                        putExtra(LaporanTableViewActivity.REQ_LAP_KEU, requestLapKeu)
                        putExtra(LaporanTableViewActivity.URL_REQ, "report/rugi-laba")
                        putExtra(LaporanTableViewActivity.TITLE_REQ, "Laporan Rugi Laba")
                        startActivity(this)
                    }
                }
                .setNegativeButton("List/Daftar") { d, i ->
                    Intent(this, LaporanDataNeracaActivity::class.java).apply {
                        putExtra(LaporanDataNeracaActivity.REQUEST_PARAM, requestLapKeu)
                        startActivity(this)
                    }
                }.create().show()
        } else if (view?.tag == "npf") {
            val data01 = DataParam(
                tgl = dat,
                kode = "ao"
            )
            val requestLapKeu = RequestLapKeu(
                request = "npf",
                data01 = data01
            )

            AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Tampilkan Laporan dalam bentuk:")
                .setPositiveButton("Table") { d, i ->
                    Intent(this, LaporanTableViewActivity::class.java).apply {
                        putExtra(LaporanTableViewActivity.REQ_LAP_KEU, requestLapKeu)
                        putExtra(LaporanTableViewActivity.URL_REQ, "report/npf")
                        putExtra(LaporanTableViewActivity.TITLE_REQ, "Laporan NPF")
                        startActivity(this)
                    }
                }
                .setNegativeButton("List/Daftar") { d, i ->
                    Intent(this, LaporanDataNpfActivity::class.java).apply {
                        putExtra(LaporanDataNeracaActivity.REQUEST_PARAM, requestLapKeu)
                        startActivity(this)
                    }
                }.create().show()
        }
    }
}