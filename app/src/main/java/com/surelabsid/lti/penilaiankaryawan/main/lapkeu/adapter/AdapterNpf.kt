package com.surelabsid.lti.penilaiankaryawan.main.lapkeu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.surelabsid.lti.penilaiankaryawan.databinding.ItemAdapterNpfBinding
import com.surelabsid.lti.penilaiankaryawan.response.DataNpfItem


class AdapterNpf(private val clickItem: (DataNpfItem?) -> Unit) :
    RecyclerView.Adapter<AdapterNpf.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemAdapterNpfBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBindItem(dataNpfItem: DataNpfItem?) {

            binding.ketKode.text = "${dataNpfItem?.ket} (${dataNpfItem?.kode})"
            binding.npf.text = "NPF: ${dataNpfItem?.npf}"
            binding.jumlah.text = dataNpfItem?.jumlah

            binding.root.setOnClickListener {
                clickItem(dataNpfItem)
            }
        }
    }

    private val dataNpfList = mutableListOf<DataNpfItem?>()

    fun addItemNpf(listItem: List<DataNpfItem?>) {
        dataNpfList.addAll(listItem)
        notifyDataSetChanged()
    }

    fun searchItem(q: String) {
        val tempList = mutableListOf<DataNpfItem?>()
        tempList.addAll(dataNpfList)
        if (q.isNotEmpty()) {
            val searchList = dataNpfList.filter {
                it?.ket?.contains(q, true) == true
            }
            dataNpfList.clear()
            dataNpfList.addAll(searchList)
        } else {
            dataNpfList.clear()
            dataNpfList.addAll(tempList)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataNpfList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAdapterNpfBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindItem(dataNpfList.get(position))
    }

}