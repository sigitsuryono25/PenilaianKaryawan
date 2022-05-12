package com.surelabsid.lti.penilaiankaryawan.main.pkp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.surelabsid.lti.penilaiankaryawan.databinding.ItemAdapterKaryawanBinding
import com.surelabsid.lti.penilaiankaryawan.response.DataKaryawanItem


class AdapterKaryawan(private val click: (DataKaryawanItem?) -> Unit) :
    RecyclerView.Adapter<AdapterKaryawan.ViewHolder>() {

    private val listKaryawan: MutableList<DataKaryawanItem?> = mutableListOf()

    inner class ViewHolder(val mItemAdapterKaryawanBinding: ItemAdapterKaryawanBinding) :
        RecyclerView.ViewHolder(mItemAdapterKaryawanBinding.root) {
        fun onBindItem(dataKaryawanItem: DataKaryawanItem?) {
            mItemAdapterKaryawanBinding.namaKaryawan.text = dataKaryawanItem?.nama
            mItemAdapterKaryawanBinding.nip.text = dataKaryawanItem?.userid

            mItemAdapterKaryawanBinding.root.setOnClickListener {
                click(dataKaryawanItem)
            }
        }
    }

    fun addItem(itemList: List<DataKaryawanItem?>, clearFirst: Boolean = false) {
        if (clearFirst)
            listKaryawan.removeAll(listKaryawan)

        listKaryawan.addAll(itemList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAdapterKaryawanBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindItem(listKaryawan[position])
    }

    override fun getItemCount(): Int {
        return listKaryawan.size
    }

}