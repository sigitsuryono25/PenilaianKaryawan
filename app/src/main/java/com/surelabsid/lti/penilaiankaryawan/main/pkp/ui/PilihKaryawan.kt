package com.surelabsid.lti.penilaiankaryawan.main.pkp.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.surelabsid.lti.penilaiankaryawan.R
import com.surelabsid.lti.penilaiankaryawan.databinding.FragmentPilihKaryawanBinding
import com.surelabsid.lti.penilaiankaryawan.main.pkp.DaftarKaryawanActivity
import com.surelabsid.lti.penilaiankaryawan.main.pkp.PkpViewModel
import com.surelabsid.lti.penilaiankaryawan.response.DataKaryawanItem
import com.surelabsid.lti.penilaiankaryawan.response.ResponseJabatan
import es.dmoral.toasty.Toasty


class PilihKaryawan : Fragment(R.layout.fragment_pilih_karyawan) {

    private lateinit var binding: FragmentPilihKaryawanBinding
    private lateinit var vm: PkpViewModel
    private var selectedIdJabatan: String? = null
    private var isSelectedKar = false
    private var dataKaryawanItem: DataKaryawanItem? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPilihKaryawanBinding.bind(view)
        vm = ViewModelProvider(this).get(PkpViewModel::class.java)

        vm.dataJabatan.observe(viewLifecycleOwner) {
            setToJabatan(it)
        }

        vm.error.observe(viewLifecycleOwner) {
            setError(it)
        }

        binding.pilihKaryawan.setOnClickListener {
            Intent(requireActivity(), DaftarKaryawanActivity::class.java).apply {
                putExtra("idJabatan", selectedIdJabatan)
                startActivityForResult(this, REQ_KARYAWAN)
            }
        }

        binding.lanjut.setOnClickListener {
            if (!isSelectedKar) {
                Toasty.warning(requireActivity(), "Pilih Karyawan Terlebih Dahulu").show()
                return@setOnClickListener
            }

            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.containerPkp, PenilaianKaryawan.newInstance(dataKaryawanItem, selectedIdJabatan))
                .commit()
        }

        getDataJabatan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_KARYAWAN && resultCode == Activity.RESULT_OK) {
            dataKaryawanItem = data?.getParcelableExtra(DaftarKaryawanActivity.DATA_KARYAWAN)
            binding.pilihKaryawan.text = dataKaryawanItem?.nama

            isSelectedKar = true
        }
    }

    private fun setToJabatan(responseJabatan: ResponseJabatan) {
        val listSpinner = mutableListOf<String?>()
        val data = responseJabatan.dataJabatan

        data?.map {
            listSpinner.add(it?.namaJabatan)
        }

        //setListenerSpinner
        binding.spinnerJabatan.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val filterData = data?.filter {
                        it?.namaJabatan.toString() == binding.spinnerJabatan.selectedItem.toString()
                    }
                    selectedIdJabatan = filterData?.iterator()?.next()?.idJabatan
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }

        setToAdapterSpinner(listSpinner)
    }

    private fun setToAdapterSpinner(listSpinner: MutableList<String?>) {
        val arrayAdapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, listSpinner)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1)
        binding.spinnerJabatan.adapter = arrayAdapter
    }

    private fun setError(throwable: Throwable?) {
        Toasty.error(requireActivity(), throwable?.message.toString()).show()
    }


    private fun getDataJabatan() {
        vm.getJabatan()
    }

    companion object {
        const val REQ_KARYAWAN = 1030
    }

}