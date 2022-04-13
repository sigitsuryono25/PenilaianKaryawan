package com.surelabsid.lti.penilaiankaryawan.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseDataNoa(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("rcode")
	val rcode: String? = null,

	@field:SerializedName("data01")
	val data01: List<DataNoaItem?>? = null
) : Parcelable

@Parcelize
data class DataNoaItem(

	@field:SerializedName("no")
	val no: String? = null,

	@field:SerializedName("kode")
	val kode: String? = null,

	@field:SerializedName("noapby")
	val noapby: String? = null,

	@field:SerializedName("noatab")
	val noatab: String? = null,

	@field:SerializedName("ket")
	val ket: String? = null,

	@field:SerializedName("droping")
	val droping: String? = null,

	@field:SerializedName("noadeb")
	val noadeb: String? = null
) : Parcelable
