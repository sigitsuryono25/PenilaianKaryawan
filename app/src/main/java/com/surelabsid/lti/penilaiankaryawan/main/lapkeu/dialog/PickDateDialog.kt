package com.surelabsid.lti.penilaiankaryawan.main.lapkeu.dialog

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.surelabsid.lti.penilaiankaryawan.R
import com.surelabsid.lti.penilaiankaryawan.main.lapkeu.LaporanTableViewActivity
import com.surelabsid.lti.penilaiankaryawan.model.DataParam
import com.surelabsid.lti.penilaiankaryawan.model.RequestLapKeu
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import java.util.*

private const val TAG_LAPORAN = "tagLaporan"


class PickDateDialog : BottomSheetDialogFragment(), DatePickerDialog.OnDateSetListener {
    private var tagLaporan: String? = null
    private var behavior: BottomSheetBehavior<View>? = null
    private lateinit var now: Calendar
    private lateinit var tgl1: TextView
    private lateinit var tgl2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            tagLaporan = it.getString(TAG_LAPORAN)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val bottomSheets = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        val view = View.inflate(context, R.layout.fragment_pick_date_dialog, null)
        val root = view.findViewById<LinearLayout>(R.id.root)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        root.layoutParams = params
        bottomSheets.setContentView(view)
        behavior = BottomSheetBehavior.from(view.parent as View)

        // prevent from dragging
        bottomSheets.setOnShowListener {
            val bottomSheet =
                (it as BottomSheetDialog).findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?
            val behavior = BottomSheetBehavior.from(bottomSheet!!)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED

            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                        behavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {}
            })
        }
        now = Calendar.getInstance()

        initViews(view)

        return bottomSheets
    }

    private fun initViews(view: View) {
        tgl1 = view.findViewById(R.id.tgl1)
        tgl2 = view.findViewById(R.id.tgl2)
        val showReport = view.findViewById<Button>(R.id.showLaporan)

        val dpd = DatePickerDialog.newInstance(
            this,
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH)
        )


        tgl1.setOnClickListener {
            dpd.show(childFragmentManager, "tgl1")
        }

        tgl2.setOnClickListener {
            dpd.show(childFragmentManager, "tgl2")
        }

        showReport.setOnClickListener {
            this@PickDateDialog.showReport(tagLaporan)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(tagLaporan: String) =
            PickDateDialog().apply {
                arguments = Bundle().apply {
                    putString(TAG_LAPORAN, tagLaporan)
                }
            }

        const val ANGSURAN = "angsuran"
        const val NOA = "noa"
        const val RATA_SALDO_TABUNGAN_DEPOSITO = "rata2tabungansaldodeposito"
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val dat = String.format("%d%02d%02d", year, monthOfYear + 1, dayOfMonth)
        when (view?.tag) {
            "tgl1" -> {
                tgl1.text = dat
            }
            "tgl2" -> {
                tgl2.text = dat
            }
        }
    }

    private fun showReport(tagLaporan: String?) {
        val report = Intent(requireActivity(), LaporanTableViewActivity::class.java)
        val data01 = DataParam(
            tgl1 = tgl1.text.toString(),
            tgl2 = tgl2.text.toString(),
            kode = "ao"
        )
        when (tagLaporan) {
            ANGSURAN -> {
                val requestLapKeu = RequestLapKeu(
                    request = "angs",
                    data01 = data01
                )
                report.apply {
                    putExtra(LaporanTableViewActivity.REQ_LAP_KEU, requestLapKeu)
                    putExtra(LaporanTableViewActivity.URL_REQ, "report/angsuran")
                    putExtra(LaporanTableViewActivity.TITLE_REQ, "Laporan Angsuran")
                }
            }
            NOA -> {
                val requestLapKeu = RequestLapKeu(
                    request = "noa",
                    data01 = data01
                )
                report.apply {
                    putExtra(LaporanTableViewActivity.REQ_LAP_KEU, requestLapKeu)
                    putExtra(LaporanTableViewActivity.URL_REQ, "report/noa")
                    putExtra(LaporanTableViewActivity.TITLE_REQ, "Laporan NOA")
                }
            }
            RATA_SALDO_TABUNGAN_DEPOSITO -> {
                val requestLapKeu = RequestLapKeu(
                    request = "saldo",
                    data01 = data01
                )
                report.apply {
                    putExtra(LaporanTableViewActivity.REQ_LAP_KEU, requestLapKeu)
                    putExtra(LaporanTableViewActivity.URL_REQ, "report/saldo-tabungan-deposito")
                    putExtra(LaporanTableViewActivity.TITLE_REQ, "Laporan Sal Tabungan & Deposito")
                }
            }
        }

        startActivity(report)
        dismiss()
    }
}