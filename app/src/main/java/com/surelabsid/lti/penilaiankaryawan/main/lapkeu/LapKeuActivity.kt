package com.surelabsid.lti.penilaiankaryawan.main.lapkeu

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.surelabsid.lti.penilaiankaryawan.databinding.ActivityLapKeuBinding
import com.surelabsid.lti.penilaiankaryawan.main.lapkeu.dialog.PickDateDialog
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

        binding.noa.setOnClickListener {
            val pickDate = PickDateDialog.newInstance(PickDateDialog.NOA)
            pickDate.show(supportFragmentManager, PickDateDialog.NOA)
        }

        binding.angsuran.setOnClickListener {
            val pickDate = PickDateDialog.newInstance(PickDateDialog.ANGSURAN)
            pickDate.show(supportFragmentManager, PickDateDialog.ANGSURAN)
        }

        binding.saldoDeposit.setOnClickListener {
            val pickDate = PickDateDialog.newInstance(PickDateDialog.RATA_SALDO_TABUNGAN_DEPOSITO)
            pickDate.show(supportFragmentManager, PickDateDialog.RATA_SALDO_TABUNGAN_DEPOSITO)

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
        when (view?.tag) {
            "neraca" -> {
                val data01 = DataParam(
                    tgl = dat,
                    golac = "nrc",
                    kdloc = "01"
                )
                val requestLapKeu = RequestLapKeu(
                    request = "lapkeu2",
                    data01 = data01
                )
                Intent(this, LaporanTableViewActivity::class.java).apply {
                    putExtra(LaporanTableViewActivity.REQ_LAP_KEU, requestLapKeu)
                    putExtra(LaporanTableViewActivity.URL_REQ, "report/neraca")
                    putExtra(LaporanTableViewActivity.TITLE_REQ, "Laporan Neraca")
                    startActivity(this)
                }
            }
            "laba-rugi" -> {
                val data01 = DataParam(
                    tgl = dat,
                    golac = "lr",
                    kdloc = "01"
                )
                val requestLapKeu = RequestLapKeu(
                    request = "lapkeu2",
                    data01 = data01
                )
                Intent(this, LaporanTableViewActivity::class.java).apply {
                    putExtra(LaporanTableViewActivity.REQ_LAP_KEU, requestLapKeu)
                    putExtra(LaporanTableViewActivity.URL_REQ, "report/rugi-laba")
                    putExtra(LaporanTableViewActivity.TITLE_REQ, "Laporan Laba Rugi")
                    startActivity(this)
                }

            }
            "npf" -> {
                val data01 = DataParam(
                    tgl = dat,
                    kode = "ao"
                )
                val requestLapKeu = RequestLapKeu(
                    request = "npf",
                    data01 = data01
                )

                Intent(this, LaporanTableViewActivity::class.java).apply {
                    putExtra(LaporanTableViewActivity.REQ_LAP_KEU, requestLapKeu)
                    putExtra(LaporanTableViewActivity.URL_REQ, "report/npf")
                    putExtra(LaporanTableViewActivity.TITLE_REQ, "Laporan NPF")
                    startActivity(this)
                }
            }
        }
    }
}