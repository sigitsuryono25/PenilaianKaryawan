package com.surelabsid.lti.penilaiankaryawan.main.home

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pixplicity.easyprefs.library.Prefs
import com.surelabsid.lti.penilaiankaryawan.R
import com.surelabsid.lti.penilaiankaryawan.databinding.FragmentHomeBinding
import com.surelabsid.lti.penilaiankaryawan.main.monitoring.PenilaianActivity
import com.surelabsid.lti.penilaiankaryawan.main.pengumuman.AllPengumumanActivity
import com.surelabsid.lti.penilaiankaryawan.main.pengumuman.DetailPengumumanActivity
import com.surelabsid.lti.penilaiankaryawan.main.pengumuman.PengumumanViewModel
import com.surelabsid.lti.penilaiankaryawan.main.pengumuman.adapter.AdapterPengumuman
import com.surelabsid.lti.penilaiankaryawan.main.pkp.PkpActivity
import com.surelabsid.lti.penilaiankaryawan.response.ResponsePengumuman
import com.surelabsid.lti.penilaiankaryawan.utils.Constant
import es.dmoral.toasty.Toasty


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var vm: PengumumanViewModel
    private lateinit var adapterPengumuman: AdapterPengumuman

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        vm = ViewModelProvider(this).get(PengumumanViewModel::class.java)

        vm.getLatestNews()
        vm.data.observe(viewLifecycleOwner) {
            setToView(it)
        }
        vm.error.observe(viewLifecycleOwner) {
            showError(it)
        }
        vm.isLoading.observe(viewLifecycleOwner) {
            if (it == View.GONE) {
                binding.rvPengumuman.visibility = View.VISIBLE
                binding.shimmerPengumuman.stopShimmer()
                binding.shimmerPengumuman.visibility = View.GONE
            } else {
                binding.rvPengumuman.visibility = View.GONE
                binding.shimmerPengumuman.startShimmer()
                binding.shimmerPengumuman.visibility = View.VISIBLE
            }
        }

        adapterPengumuman = AdapterPengumuman {
            Intent(requireActivity(), DetailPengumumanActivity::class.java).apply {
                putExtra("dataPengumuman", it)
                startActivity(this)
            }
        }
        binding.rvPengumuman.apply {
            adapter = adapterPengumuman
            layoutManager = LinearLayoutManager(requireActivity())
        }

        binding.selengkapnya.setOnClickListener {
            Intent(requireActivity(), AllPengumumanActivity::class.java).apply {
                startActivity(this)
            }
        }

        binding.shimmerPengumuman.startShimmer()

        binding.pkp.setOnClickListener {
            if (Prefs.getString(Constant.JABATAN).equals("7")) {
                setAlert("Menu tidak tersedia dilevel anda", requireActivity())
                return@setOnClickListener
            }
            Intent(requireActivity(), PkpActivity::class.java).apply {
                startActivity(this)
            }
        }

        binding.monitoring.setOnClickListener {
            Intent(requireActivity(), PenilaianActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    private fun showError(throwable: Throwable) {
        Toasty.error(requireActivity(), throwable.message.toString()).show()
    }

    private fun setToView(pengumumanDummy: ResponsePengumuman) {
        pengumumanDummy.dataPengumuman?.take(5)?.let { adapterPengumuman.addItem(it) }
    }

    companion object {
        fun setAlert(message: String, context: Context) {
            AlertDialog.Builder(context)
                .setMessage(message)
                .setTitle("Akses Ditolak")
                .setPositiveButton("ok") { _, _ ->
                }
                .create().show()
        }
    }

}