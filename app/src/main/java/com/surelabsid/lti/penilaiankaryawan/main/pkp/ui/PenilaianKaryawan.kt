package com.surelabsid.lti.penilaiankaryawan.main.pkp.ui

import android.graphics.Typeface
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pixplicity.easyprefs.library.Prefs
import com.surelabsid.lti.penilaiankaryawan.R
import com.surelabsid.lti.penilaiankaryawan.databinding.FragmentPenilaianKaryawanBinding
import com.surelabsid.lti.penilaiankaryawan.main.pkp.PkpViewModel
import com.surelabsid.lti.penilaiankaryawan.model.Nilai
import com.surelabsid.lti.penilaiankaryawan.model.Penilaian
import com.surelabsid.lti.penilaiankaryawan.model.PenilaianSend
import com.surelabsid.lti.penilaiankaryawan.response.*
import com.surelabsid.lti.penilaiankaryawan.utils.Constant
import es.dmoral.toasty.Toasty

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PenilaianKaryawan : Fragment(R.layout.fragment_penilaian_karyawan) {

    private var param1: DataKaryawanItem? = null
    private var param2: String? = null
    private lateinit var binding: FragmentPenilaianKaryawanBinding
    private lateinit var vm: PkpViewModel
    private var initIndex = 0
    private var bobotList = mutableListOf<String?>()
    private var idPoint = mutableListOf<String?>()
    private var givenScore = mutableListOf<String?>()
    private var bobotBidang: String? = null
    private var idBidang: String? = null
    private var namaBidang: String? = null
    private var dataParam: List<DataParamItem?>? = null
    private var penilaianList = mutableListOf<Penilaian>()
    private var subpoint: List<SubpointItem?>? = listOf<SubpointItem>()
    private var pointItemItem: List<PointItemItem?>? = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelable(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        vm = ViewModelProvider(this).get(PkpViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPenilaianKaryawanBinding.bind(view)

        vm.dataParams.observe(viewLifecycleOwner) {
            setToView(it)
        }

        vm.error.observe(viewLifecycleOwner) {
            setError(it)
        }

        vm.generalRes.observe(viewLifecycleOwner) {
            AlertDialog.Builder(requireActivity())
                .setMessage(it.message)
                .setTitle("Info")
                .setPositiveButton("Oke") { d, _ ->
                    requireActivity().finish()
                    d.dismiss()
                    Prefs.remove(Constant.IS_SELECTED_KAR)
                }
                .create().show()
        }

        getParams()

        binding.lanjut.setOnClickListener {
            getAllValues()
        }

    }

    private fun getAllValues() {
        initIndex++
        if (initIndex < this.dataParam?.size!!) {
            val tampunganNilaiSementara = mutableListOf<Nilai>()
            val penilaianLayout = binding.containerPenilaian
            val nilaiPerbidang = mutableListOf<Double>()
            val givenScore = mutableListOf<Double>()
            val edtPenilaian = mutableListOf<EditText>()
            val pairNilaiXIdPoint = hashMapOf<String?, Double>()
            for (i in 0 until penilaianLayout.childCount) {
                if (penilaianLayout.getChildAt(i) is EditText) {
                    edtPenilaian.add(penilaianLayout.getChildAt(i) as EditText)
                }
            }
            var jumlah = 0.0

            edtPenilaian.forEachIndexed { i, it ->
                if(it.text.isEmpty()){
                    Toasty.warning(requireActivity(), "isi semua kolom nilai terlebih dahulu").show()
                    return
                }
                val nilai = it.text.toString().toDouble()
                val b = bobotList[i].toString().toDouble()
                val akhir = nilai.times(b)
                nilaiPerbidang.add(akhir)
                givenScore.add(it.text.toString().toDouble())
                jumlah += akhir
            }

            idPoint.forEachIndexed { i, d ->
                pairNilaiXIdPoint[d] = nilaiPerbidang.get(i)
                val nilai = Nilai()
                nilai.idPoint = d
                nilai.nilai = nilaiPerbidang[i]
                nilai.giveScore = givenScore[i]

                tampunganNilaiSementara.add(nilai)
            }

            val bb = bobotBidang.toString().toDouble()
            binding.jumlah.visibility = View.GONE


            val penilaian = Penilaian()
            penilaian.pairNilaiXidPoint = tampunganNilaiSementara
            penilaian.bobotPoint = bobotBidang
            penilaian.nilaiAKhirPerPoint = String.format("%.2f", jumlah.times(bb))
            penilaian.idBidang = idBidang

            penilaianList.add(penilaian)

            getPoint(initIndex)

        } else {
            val tampunganNilaiSementara = mutableListOf<Nilai>()
            val penilaianLayout = binding.containerPenilaian
            val nilaiPerbidang = mutableListOf<Double>()
            val edtPenilaian = mutableListOf<EditText>()
            val givenScore = mutableListOf<Double>()
            val pairNilaiXIdPoint = hashMapOf<String?, Double>()
            for (i in 0 until penilaianLayout.childCount) {
                if (penilaianLayout.getChildAt(i) is EditText) {
                    edtPenilaian.add(penilaianLayout.getChildAt(i) as EditText)
                }
            }
            var jumlah = 0.0
            edtPenilaian.forEachIndexed { i, it ->
                if(it.text.isEmpty()){
                    Toasty.warning(requireActivity(), "isi semua kolom nilai terlebih dahulu").show()
                    return
                }
                val nilai = it.text.toString().toDouble()
                val b = bobotList[i].toString().toDouble()
                val akhir = nilai.times(b)
                nilaiPerbidang.add(akhir)
                givenScore.add(it.text.toString().toDouble())
                jumlah += akhir
            }

            idPoint.forEachIndexed { i, d ->
                pairNilaiXIdPoint[d] = nilaiPerbidang.get(i)
                val nilai = Nilai()
                nilai.idPoint = d
                nilai.nilai = nilaiPerbidang[i]
                nilai.giveScore = givenScore[i]

                tampunganNilaiSementara.add(nilai)
            }

            val bb = bobotBidang.toString().toDouble()
            binding.jumlah.visibility = View.GONE


            val penilaian = Penilaian()
            penilaian.pairNilaiXidPoint = tampunganNilaiSementara
            penilaian.bobotPoint = bobotBidang
            penilaian.nilaiAKhirPerPoint = String.format("%.2f", jumlah.times(bb))
            penilaian.idBidang = idBidang


            penilaianList.add(penilaian)

            val penilaianSend = PenilaianSend()
            penilaianSend.userid = param1?.userid
            penilaianSend.penilaian = penilaianList
            penilaianSend.dinilaiOleh = Prefs.getString(Constant.USERID)

            vm.sendPenilaian(penilaianSend)
        }
    }


    private fun setError(throwable: Throwable) {
        throwable.printStackTrace()
        Toasty.error(requireActivity(), throwable.message.toString()).show()
    }

    private fun setToView(responseParams: ResponseParams) {
        this.dataParam = responseParams.dataParam
        getPoint(initIndex)
    }

    private fun getPoint(initIndex: Int) {
        bobotList = mutableListOf()
        idPoint = mutableListOf()
        subpoint = listOf()
        pointItemItem = listOf()
        val titleName = dataParam?.get(initIndex)?.nama
        bobotBidang = dataParam?.get(initIndex)?.bobotBidang
        idBidang = dataParam?.get(initIndex)?.idBidang
        namaBidang = titleName
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
        point?.forEachIndexed { i, d ->

            val urutan = charA.plus(i.plus(1)).toChar()
            val tv = TextView(requireActivity())
            val c = LinearLayout(requireActivity())
            val cc = LinearLayout(requireActivity())
            val bobotTextView = TextView(requireActivity())

            val edittext = EditText(requireContext())
            edittext.layoutParams = params
            edittext.setBackgroundColor(
                ActivityCompat.getColor(
                    requireContext(),
                    android.R.color.transparent
                )
            )
            edittext.hint = "Tulis Score Disini"
            edittext.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL

            c.orientation = LinearLayout.VERTICAL
            cc.orientation = LinearLayout.VERTICAL
            tv.text = "$urutan. ${d?.isiPoint}"
            tv.layoutParams = params
            tv.setPadding(8, 8, 8, 8)
            tv.setTextAppearance(android.R.style.TextAppearance_Material_Body2)

            //first, add bobot to list
            bobotList.add(d?.bobot)
            idPoint.add(d?.idPoint)

            bobotTextView.text = "Bobot: ${d?.bobot}"
            bobotTextView.setTextColor(
                ActivityCompat.getColor(
                    requireContext(),
                    android.R.color.holo_red_light
                )
            )
            bobotTextView.textSize = 16f
            bobotTextView.typeface = Typeface.DEFAULT_BOLD
            bobotTextView.layoutParams = params
            bobotTextView.gravity = GravityCompat.END

            //asign subpoint
            subpoint = d?.subpoint

            d?.subpoint?.forEachIndexed { i2, d2 ->
                val subp = TextView(requireActivity())
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
        }
    }

    private fun getParams() {
        vm.getParams(param2)
    }

    companion object {
        @JvmStatic
        fun newInstance(dataKaryawanItem: DataKaryawanItem?, idJabatan: String?) =
            PenilaianKaryawan().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, dataKaryawanItem)
                    putString(ARG_PARAM2, idJabatan)
                }
            }
    }
}