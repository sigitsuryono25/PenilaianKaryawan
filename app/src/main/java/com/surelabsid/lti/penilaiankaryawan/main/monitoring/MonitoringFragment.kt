package com.surelabsid.lti.penilaiankaryawan.main.monitoring

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pixplicity.easyprefs.library.Prefs
import com.surelabsid.lti.penilaiankaryawan.R
import com.surelabsid.lti.penilaiankaryawan.databinding.ActivityMonitoringBinding
import com.surelabsid.lti.penilaiankaryawan.main.monitoring.adapter.AdapterDaftarPenilaian
import com.surelabsid.lti.penilaiankaryawan.response.ResponsePenilaian
import com.surelabsid.lti.penilaiankaryawan.utils.Constant
import es.dmoral.toasty.Toasty
import java.util.*

class MonitoringFragment : Fragment(R.layout.activity_monitoring) {
    private lateinit var binding: ActivityMonitoringBinding
    private lateinit var vm: PenilaianViewModel
    private lateinit var adapterDaftarPenilaian: AdapterDaftarPenilaian

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ActivityMonitoringBinding.bind(view)

        vm = ViewModelProvider(this)[PenilaianViewModel::class.java]

        initializeYear()

        vm.data.observe(requireActivity()) {
            setToView(it)
        }
        vm.error.observe(requireActivity()) {
            setError(it)
        }
        initializeAdapter()

        binding.dropDownTahun.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                getData(parent?.selectedItem.toString().toInt())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }
    private fun initializeAdapter(){
        adapterDaftarPenilaian = AdapterDaftarPenilaian {
            Intent(requireActivity(), DetailPenilaianActivity::class.java).apply {
                putExtra("data_penilaian", it)
                startActivity(this)
            }
        }
        binding.daftarPenilaian.adapter = adapterDaftarPenilaian
        binding.daftarPenilaian.layoutManager = LinearLayoutManager(requireActivity())
    }

    private fun setError(throwable: Throwable) {
        binding.daftarPenilaian.adapter = null
        Toasty.error(requireActivity(), throwable.message.toString()).show()
    }

    private fun setToView(responsePenilaian: ResponsePenilaian) {
        initializeAdapter()
        responsePenilaian.dataPenilaian?.let { adapterDaftarPenilaian.addData(it, true) }
    }

    private fun initializeYear() {
        var listOfYear = mutableListOf<Int>()
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        for (i in year downTo 2021) {
            listOfYear.add(i)
        }

        val adapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, listOfYear)
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1)
        binding.dropDownTahun.adapter = adapter

        getData(binding.dropDownTahun.selectedItem.toString().toInt())
    }

    private fun getData(y: Int) {
        if (Prefs.getStringSet(Constant.RULES, mutableSetOf()).isEmpty()) {
            vm.getPenilaianByUser(Prefs.getString(Constant.USERID), y)
        } else {
            vm.getPenilaian(Prefs.getString(Constant.USERID), y)
        }
    }
}