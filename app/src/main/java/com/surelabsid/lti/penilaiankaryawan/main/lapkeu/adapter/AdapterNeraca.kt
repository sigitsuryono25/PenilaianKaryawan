package com.surelabsid.lti.penilaiankaryawan.main.lapkeu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.surelabsid.lti.penilaiankaryawan.databinding.ItemAdapterNeracaBinding
import com.surelabsid.lti.penilaiankaryawan.response.DataNeracaItem

class AdapterNeraca : RecyclerView.Adapter<AdapterNeraca.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemAdapterNeracaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBindItem(dataNeracaItem: DataNeracaItem?) {
            binding.nmsbb.text = dataNeracaItem?.nmsbb
            binding.tanggalBulanTahun.text =
                "${dataNeracaItem?.tgl}/${dataNeracaItem?.bln}/${dataNeracaItem?.thn}"
            binding.saldo.text = dataNeracaItem?.saldo
        }
    }

    private val dataNeracaList = mutableListOf<DataNeracaItem?>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAdapterNeracaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun addItem(listNeraca: List<DataNeracaItem?>) {
        dataNeracaList.addAll(listNeraca)
        notifyDataSetChanged()
    }

    fun searchItem(q: String) {
        val tempList = mutableListOf<DataNeracaItem?>()
        tempList.addAll(dataNeracaList)
        if (q.length >= 3) {
            val searchList = dataNeracaList.filter {
                it?.nmsbb?.contains(q, true) == true
            }
            dataNeracaList.clear()
            dataNeracaList.addAll(searchList)
        } else {
            dataNeracaList.clear()
            dataNeracaList.addAll(tempList)
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindItem(dataNeracaList.get(position))
    }

    override fun getItemCount(): Int {
        return dataNeracaList.size
    }


}