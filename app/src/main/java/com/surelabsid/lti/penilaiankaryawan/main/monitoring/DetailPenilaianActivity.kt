package com.surelabsid.lti.penilaiankaryawan.main.monitoring

import android.graphics.Typeface
import android.os.Bundle
import android.text.InputType
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import com.pixplicity.easyprefs.library.Prefs
import com.surelabsid.lti.penilaiankaryawan.R
import com.surelabsid.lti.penilaiankaryawan.databinding.ActivityDetailPenilaianBinding
import com.surelabsid.lti.penilaiankaryawan.main.pkp.PkpViewModel
import com.surelabsid.lti.penilaiankaryawan.response.DataParamItem
import com.surelabsid.lti.penilaiankaryawan.response.DataPenilaianItem
import com.surelabsid.lti.penilaiankaryawan.response.PenilaianItem
import com.surelabsid.lti.penilaiankaryawan.response.ResponseParams
import com.surelabsid.lti.penilaiankaryawan.utils.Constant
import es.dmoral.toasty.Toasty

class DetailPenilaianActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPenilaianBinding
    private lateinit var vm: PkpViewModel
    private var initIndex = 0
    private var dataParam: List<DataParamItem?>? = null
    private var bobotBidang: String? = null
    private var dataPenilaianItem: List<PenilaianItem?>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPenilaianBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val incomingData = intent.getParcelableExtra<DataPenilaianItem>("data_penilaian")

        supportActionBar?.apply {
            if (Prefs.getString(Constant.JABATAN).equals("7")) {
                title = "Detail Hasil Penilaian Anda"
            }else {
                title = "Detail Penilaian Karyawan"
            }
            setDisplayHomeAsUpEnabled(true)
            subtitle = incomingData?.namaKar?.trim()
        }

        vm = ViewModelProvider(this).get(PkpViewModel::class.java)

        dataPenilaianItem = incomingData?.penilaian

        binding.lanjut.setOnClickListener {
            initIndex++
            if (initIndex < this.dataParam?.size!!)
                getPoint(initIndex)
            else
                finish()
        }

        getParams(incomingData?.jabatanKar)
        vm.dataParams.observe(this) {
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
        throwable.printStackTrace()
        Toasty.error(this, throwable.message.toString()).show()
    }

    private fun setToView(responseParams: ResponseParams) {
        this.dataParam = responseParams.dataParam
        getPoint(initIndex)
    }


    private fun getPoint(initIndex: Int) {
        val titleName = dataParam?.get(initIndex)?.nama
        bobotBidang = dataParam?.get(initIndex)?.bobotBidang
        binding.bidang.text = titleName
        binding.bobotBidang.text = "Bobot bidang: $bobotBidang"
        val point = dataParam?.get(initIndex)?.point
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        val charA = 96
        binding.containerPenilaian.removeAllViews()
        binding.scrollContainer.scrollTo(0, 0)
        var total = 0.0
        point?.forEachIndexed { i, d ->

            val urutan = charA.plus(i.plus(1)).toChar()
            val tv = TextView(this)
            val c = LinearLayout(this)
            val cc = LinearLayout(this)
            val bobotTextView = TextView(this)
            val rincian = TextView(this)

            val edittext = TextView(this)
            edittext.layoutParams = params
            edittext.setBackgroundColor(
                ActivityCompat.getColor(
                    this,
                    android.R.color.transparent
                )
            )
            edittext.hint = "Tulis Score Disini"
            edittext.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL

            val filterIdBidang = dataPenilaianItem?.filter {
                it?.idBidang == dataParam?.get(initIndex)?.idBidang
            }

            val nilai = filterIdBidang?.get(0)?.pairNilaiXidPoint?.filter {
                it?.idPoint == d?.idPoint
            }

            val nilalFinal = nilai?.get(0)?.nilai
            edittext.typeface = Typeface.DEFAULT_BOLD
            edittext.gravity = GravityCompat.END
            edittext.text = String.format(
                "Score yang diberikan: %.2f",
                nilalFinal?.div(d?.bobot?.toDouble()!!)
            )


            c.orientation = LinearLayout.VERTICAL
            cc.orientation = LinearLayout.VERTICAL
            tv.text = "$urutan. ${d?.isiPoint}"
            tv.layoutParams = params
            tv.setPadding(8, 8, 8, 8)
            tv.setTextAppearance(android.R.style.TextAppearance_Material_Body2)

            total = total.plus(nilalFinal!!)

            binding.jumlah.text =
                String.format("Jumlah nilai: %.2f",total.times(bobotBidang?.toDouble()!!))

            rincian.text = String.format("Rincian: %.2f", nilalFinal)
            rincian.setTextColor(
                ActivityCompat.getColor(
                    this,
                    R.color.blue_500
                )
            )
            rincian.typeface = Typeface.DEFAULT_BOLD
            rincian.layoutParams = params
            rincian.gravity = GravityCompat.END

            bobotTextView.text = "Bobot: ${d?.bobot}"
            bobotTextView.setTextColor(
                ActivityCompat.getColor(
                    this,
                    android.R.color.holo_red_light
                )
            )
            bobotTextView.typeface = Typeface.DEFAULT_BOLD
            bobotTextView.layoutParams = params
            bobotTextView.gravity = GravityCompat.END


            d?.subpoint?.forEachIndexed { i2, d2 ->
                val subp = TextView(this)
                subp.setPadding(50, 5, 5, 5)
                subp.text = "$urutan.${i2.plus(1)}. ${d2?.isiSubPoint}"
                subp.layoutParams = params
                subp.setTextAppearance(android.R.style.TextAppearance_Material_Body2)
                cc.layoutParams = params
                cc.addView(subp, -1)
            }

            c.addView(tv, -1)
            c.addView(cc, -1)
            val containerParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            containerParams.setMargins(0, 20, 0, 0)
            c.layoutParams = containerParams
            binding.containerPenilaian.addView(c, -1)
            binding.containerPenilaian.addView(edittext, -1)
            binding.containerPenilaian.addView(bobotTextView, -1)
            binding.containerPenilaian.addView(rincian, -1)
        }
    }

    private fun getParams(jabatanKar: String?) {
        vm.getParams(jabatanKar)
    }
}