package com.surelabsid.lti.penilaiankaryawan.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseDataNPF(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("rcode")
	val rcode: String? = null,

	@field:SerializedName("data01")
	val data01: List<DataNpfItem?>? = null
) : Parcelable

@Parcelize
data class DataNpfItem(

	@field:SerializedName("coll3")
	val coll3: String? = null,

	@field:SerializedName("no")
	val no: String? = null,

	@field:SerializedName("coll2")
	val coll2: String? = null,

	@field:SerializedName("coll1")
	val coll1: String? = null,

	@field:SerializedName("npf")
	val npf: String? = null,

	@field:SerializedName("qty3")
	val qty3: String? = null,

	@field:SerializedName("qty2")
	val qty2: String? = null,

	@field:SerializedName("qty5")
	val qty5: String? = null,

	@field:SerializedName("qty4")
	val qty4: String? = null,

	@field:SerializedName("qty1")
	val qty1: String? = null,

	@field:SerializedName("coll5")
	val coll5: String? = null,

	@field:SerializedName("coll4")
	val coll4: String? = null,

	@field:SerializedName("jumlah")
	val jumlah: String? = null,

	@field:SerializedName("kode")
	val kode: String? = null,

	@field:SerializedName("ket")
	val ket: String? = ""
) : Parcelable
