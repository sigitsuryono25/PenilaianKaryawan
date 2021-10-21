package com.surelabsid.lti.penilaiankaryawan.main.pengumuman.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.surelabsid.lti.penilaiankaryawan.databinding.ItemAdapterPengumumanBinding
import com.surelabsid.lti.penilaiankaryawan.response.DataItem

class AdapterPengumuman : RecyclerView.Adapter<AdapterPengumuman.ViewHolder>() {

    private val list = mutableListOf<DataItem?>()

    class ViewHolder(val mItemAdapterPengumumanBinding: ItemAdapterPengumumanBinding) :
        RecyclerView.ViewHolder(mItemAdapterPengumumanBinding.root) {

        fun onBindingItem(dataItem: DataItem?) {
            mItemAdapterPengumumanBinding.postOn.text = dataItem?.postOn
            mItemAdapterPengumumanBinding.title.text = dataItem?.jdlNews
            Glide.with(itemView.context)
                .load(dataItem?.fotoNews)
                .into(mItemAdapterPengumumanBinding.imgSrc)
        }
    }

    fun addItem(list: List<DataItem?>, isClear: Boolean = false) {
        if (isClear)
            this.list.removeAll(this.list)

        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAdapterPengumumanBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindingItem(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}