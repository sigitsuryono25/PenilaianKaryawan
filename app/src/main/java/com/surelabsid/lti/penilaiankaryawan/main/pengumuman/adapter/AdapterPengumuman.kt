package com.surelabsid.lti.penilaiankaryawan.main.pengumuman.adapter

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
import com.surelabsid.lti.penilaiankaryawan.R
import com.surelabsid.lti.penilaiankaryawan.databinding.ItemAdapterPengumumanBinding
import com.surelabsid.lti.penilaiankaryawan.response.DataPengumumanItem

class AdapterPengumuman(private val onItemClick: (DataPengumumanItem?) -> Unit) :
    RecyclerView.Adapter<AdapterPengumuman.ViewHolder>() {

    private val list = mutableListOf<DataPengumumanItem?>()

    inner class ViewHolder(val mItemAdapterPengumumanBinding: ItemAdapterPengumumanBinding) :
        RecyclerView.ViewHolder(mItemAdapterPengumumanBinding.root) {

        fun onBindingItem(dataItem: DataPengumumanItem?) {
            mItemAdapterPengumumanBinding.postOn.text = dataItem?.addedOn
            mItemAdapterPengumumanBinding.title.text = dataItem?.judul
            mItemAdapterPengumumanBinding.keterangan.text = Html.escapeHtml(dataItem?.keterangan)

            if(dataItem?.cover?.isEmpty() == true) {
                Glide.with(itemView.context)
                    .load(R.drawable.img_default)
                    .into(mItemAdapterPengumumanBinding.imgCover)
            }else{
                Glide.with(itemView.context)
                    .load(dataItem?.cover)
                    .into(mItemAdapterPengumumanBinding.imgCover)
            }
            itemView.setOnClickListener {
                onItemClick(dataItem)
            }

            val chipGroup = ChipGroup(itemView.context)
            val lParamsChipGroup = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            chipGroup.layoutParams = lParamsChipGroup


            dataItem?.file?.take(1)?.forEachIndexed { i, _ ->
                val chip = Chip(itemView.context)
                val lParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                chip.setChipBackgroundColorResource(R.color.blue_200)
                chip.layoutParams = lParams
                chip.text = String.format("Lampiran %d", i.plus(1))
                val chipDrawable = ChipDrawable.createFromResource(itemView.context, R.xml.chip)
                chipDrawable.chipBackgroundColor = ActivityCompat.getColorStateList(itemView.context, R.color.blue_200)
                chip.chipIcon = chipDrawable
                chipGroup.addView(chip, -1)

                chip.setOnClickListener {

                }
            }
            if (dataItem?.file?.size!! > 1) {
                moreChip(
                    itemView.context,
                    chipGroup,
                    String.format("+%d lainnya", dataItem.file.size.minus(1))
                )
            }

            mItemAdapterPengumumanBinding.lampiran.addView(chipGroup, -1)
        }

        private fun moreChip(context: Context, chipGroup: ChipGroup, message: String) {
            val chip = Chip(context)
            val lParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            chip.layoutParams = lParams
            chip.text = message
            chipGroup.addView(chip, -1)

            chip.setOnClickListener {
//                Toasty.success(context, chip.text.toString()).show()
            }
        }
    }


    fun addItem(list: List<DataPengumumanItem?>, isClear: Boolean = false) {
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