package com.surelabsid.lti.penilaiankaryawan.main.lapkeu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.surelabsid.lti.penilaiankaryawan.R
import com.surelabsid.lti.penilaiankaryawan.databinding.ActivityLapKeuBinding
import com.surelabsid.lti.penilaiankaryawan.model.DataParam
import com.surelabsid.lti.penilaiankaryawan.model.RequestLapKeu
import com.surelabsid.lti.penilaiankaryawan.response.DataKantorItem
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import timber.log.Timber
import java.util.*

class LapKeuActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private lateinit var binding: ActivityLapKeuBinding
    private lateinit var now: Calendar
    private val REQ_LOC_KANTOR = 1090
    private var dataKantorItem: DataKantorItem? = null
    private var title = emptyArray<String>()
    private var endPoint = emptyArray<String>()
    private var requestKey = emptyArray<String>()
    private var golac = emptyArray<String>()
    private var titleSelected: String? = null
    private var endPointSelected: String? = null
    private var requestKeySelected: String? = null
    private var golacSelected: String? = null
    private var tgl1: String? = null
    private var kantor: String? = null
    private var tgl2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLapKeuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        now = Calendar.getInstance()

        title = resources.getStringArray(R.array.title_report)
        endPoint = resources.getStringArray(R.array.end_point)
        requestKey = resources.getStringArray(R.array.request_data)
        golac = resources.getStringArray(R.array.golac)

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

        binding.tgl1.setOnClickListener {
            dpd.show(supportFragmentManager, "tgl1")
        }
        binding.tgl2.setOnClickListener {
            dpd.show(supportFragmentManager, "tgl2")
        }

        binding.pilihKantor.setOnClickListener {
            Intent(this@LapKeuActivity, ListKantorActivity::class.java).apply {
                startActivityForResult(this, REQ_LOC_KANTOR)
            }
        }

        binding.jenisLaporan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 3 || position == 4 || position == 5) {
                    binding.tgl2.visibility = View.VISIBLE
                } else {
                    binding.tgl2.visibility = View.GONE
                    tgl2 = ""
                }
                titleSelected = title[position]
                endPointSelected = endPoint[position]
                requestKeySelected = requestKey[position]
                golacSelected = golac[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.lihat.setOnClickListener {
            if(binding.tgl2.visibility == View.VISIBLE && tgl2?.isEmpty() == true){
                Toast.makeText(this@LapKeuActivity, "Tanggal Akhir harus Dipilih", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val data01 = DataParam(
                tgl = tgl1,
                tgl1 = tgl1,
                tgl2 = tgl2,
                golac = golacSelected.toString(),
                kdloc = dataKantorItem?.kdloc.toString()
            )
            val requestLapKeu = RequestLapKeu(
                request = requestKeySelected.toString(),
                data01 = data01
            )
            Intent(this, LaporanTableViewActivity::class.java).apply {
                putExtra(LaporanTableViewActivity.REQ_LAP_KEU, requestLapKeu)
                putExtra(LaporanTableViewActivity.URL_REQ, endPointSelected.toString())
                putExtra(LaporanTableViewActivity.TITLE_REQ, titleSelected.toString())
                putExtra(LaporanTableViewActivity.KANTOR, binding.pilihKantor.text.toString())

                /**
                 * dari android cuma kirim kategori laporannya aja, baru nanti backend yang tata
                 * data yang dikirimkan ke backend juga nama kantor/cabang untuk keperluan judul di laporan
                 * intinya, endpoint yang ditembak cuma 1, yang berubah parameter GET yang dikirimkan aja
                 * jangan sampe lupa apa yang udah dicatet ini ya
                 */
                startActivity(this)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_LOC_KANTOR && resultCode == Activity.RESULT_OK) {
            dataKantorItem = data?.getParcelableExtra(ListKantorActivity.SELECTED_RESULT)
            binding.pilihKantor.text = dataKantorItem?.nama
            Timber.d("onActivityResult: $dataKantorItem")
        }
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {

        when (view?.tag) {
            "tgl1" -> {
                val dat = String.format("%d%02d%02d", year, monthOfYear + 1, dayOfMonth)
                binding.tgl1.text = dat
                tgl1 = dat
            }
            "tgl2" -> {
                val dat = String.format("%d%02d%02d", year, monthOfYear + 1, dayOfMonth)
                binding.tgl2.text = dat
                tgl2 = dat
            }
        }
    }
}