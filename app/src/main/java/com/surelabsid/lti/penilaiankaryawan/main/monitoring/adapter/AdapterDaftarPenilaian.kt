package com.surelabsid.lti.penilaiankaryawan.main.monitoring.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.surelabsid.lti.penilaiankaryawan.databinding.ItemAdapterDaftarPenilaianBinding
import com.surelabsid.lti.penilaiankaryawan.response.DataPenilaianItem

class AdapterDaftarPenilaian(private val onClick: (DataPenilaianItem?) -> Unit) :
    RecyclerView.Adapter<AdapterDaftarPenilaian.ViewHolder>() {

    private val listPenilaian = mutableListOf<DataPenilaianItem?>()

    inner class ViewHolder(val mItemAdapterDaftarPenilaianBinding: ItemAdapterDaftarPenilaianBinding) :
        RecyclerView.ViewHolder(mItemAdapterDaftarPenilaianBinding.root) {

        fun onBindItem(responsePenilaianItem: DataPenilaianItem?) {
            var total= 0.0
            val penilaianItem = responsePenilaianItem?.dataPenilaian
            penilaianItem?.forEach {
                total = total.plus(it?.nilaiAKhirPerPoint?.toDouble()!!)
            }

            mItemAdapterDaftarPenilaianBinding.nilaiAkhir.text = String.format("Nilai akhir: %.2f", total)
            mItemAdapterDaftarPenilaianBinding.namaKaryawan.text = responsePenilaianItem?.namaKar?.trim()
            mItemAdapterDaftarPenilaianBinding.dinilaiPada.text = responsePenilaianItem?.dinilaiPada?.trim()

            mItemAdapterDaftarPenilaianBinding.root.setOnClickListener {
                onClick(responsePenilaianItem)
            }
        }

    }

    fun addData(list: List<DataPenilaianItem?>, clearIt: Boolean = false) {
        if (clearIt)
            listPenilaian.removeAll(listPenilaian)

        listPenilaian.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAdapterDaftarPenilaianBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindItem(listPenilaian[position])
    }

    override fun getItemCount(): Int {
        return listPenilaian.size
    }
}
    