package com.surelabsid.lti.penilaiankaryawan.main.lapkeu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.surelabsid.lti.penilaiankaryawan.databinding.ItemAdapterListKantorBinding
import com.surelabsid.lti.penilaiankaryawan.response.DataKantorItem

class AdapterListKantor(private val clickItem: (DataKantorItem?) -> Unit) :
    RecyclerView.Adapter<AdapterListKantor.ViewHolder>() {

    private var listKantor = mutableListOf<DataKantorItem?>()


    inner class ViewHolder(private val binding: ItemAdapterListKantorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBindItem(dataKantorItem: DataKantorItem?) {
            binding.namaKantor.text = dataKantorItem?.nama
            binding.root.setOnClickListener {
                clickItem(dataKantorItem)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAdapterListKantorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun addItem(listKantor: List<DataKantorItem?>, clearAll: Boolean = false) {
        if (clearAll)
            this.listKantor.clear()

        this.listKantor.addAll(listKantor)

        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindItem(listKantor.get(position))
    }

    override fun getItemCount(): Int {
        return listKantor.size
    }


}