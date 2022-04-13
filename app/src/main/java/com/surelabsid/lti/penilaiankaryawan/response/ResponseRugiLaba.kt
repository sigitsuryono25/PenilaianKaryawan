package com.surelabsid.lti.penilaiankaryawan.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseRugiLaba(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("rcode")
	val rcode: String? = null,

	@field:SerializedName("data01")
	val data01: List<Data01Item?>? = null
) : Parcelable

@Parcelize
data class Data01Item(

	@field:SerializedName("nosbb")
	val nosbb: String? = null,

	@field:SerializedName("no")
	val no: String? = null,

	@field:SerializedName("bln")
	val bln: String? = null,

	@field:SerializedName("nmsbb")
	val nmsbb: String? = null,

	@field:SerializedName("tgl")
	val tgl: String? = null,

	@field:SerializedName("thn")
	val thn: String? = null,

	@field:SerializedName("saldo")
	val saldo: String? = null
) : Parcelable
