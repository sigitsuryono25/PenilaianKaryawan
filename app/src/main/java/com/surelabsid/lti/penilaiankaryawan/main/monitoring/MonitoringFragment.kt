package com.surelabsid.lti.penilaiankaryawan.main.monitoring

import android.content.Intent
import android.os.Bundle
import android.view.View
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

class MonitoringFragment : Fragment(R.layout.activity_monitoring) {
    private lateinit var binding: ActivityMonitoringBinding
    private lateinit var vm: PenilaianViewModel
    private lateinit var adapterDaftarPenilaian: AdapterDaftarPenilaian

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ActivityMonitoringBinding.bind(view)

        vm = ViewModelProvider(this).get(PenilaianViewModel::class.java)
        if (Prefs.getString(Constant.JABATAN).equals("7")) {
            vm.getPenilaianByUser(Prefs.getString(Constant.USERID))
        } else {
            vm.getPenilaian(Prefs.getString(Constant.USERID))
        }

        adapterDaftarPenilaian = AdapterDaftarPenilaian {
            Intent(requireActivity(), DetailPenilaianActivity::class.java).apply {
                putExtra("data_penilaian", it)
                startActivity(this)
            }
        }
        binding.daftarPenilaian.adapter = adapterDaftarPenilaian
        binding.daftarPenilaian.layoutManager = LinearLayoutManager(requireActivity())

        vm.data.observe(requireActivity()) {
            setToView(it)
        }
        vm.error.observe(requireActivity()) {
            setError(it)
        }
    }

    private fun setError(throwable: Throwable) {
        Toasty.error(requireActivity(), throwable.message.toString()).show()
    }

    private fun setToView(responsePenilaian: ResponsePenilaian) {
        responsePenilaian.dataPenilaian?.let { adapterDaftarPenilaian.addData(it) }
    }
}