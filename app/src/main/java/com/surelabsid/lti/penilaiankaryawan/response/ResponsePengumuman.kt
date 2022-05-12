package com.surelabsid.lti.penilaiankaryawan.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponsePengumuman(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("data_pengumuman")
	val dataPengumuman: List<DataPengumumanItem?>? = null
) : Parcelable

@Parcelize
data class DataPengumumanItem(

	@field:SerializedName("keterangan")
	val keterangan: String? = null,

	@field:SerializedName("cover")
	val cover: String? = null,

	@field:SerializedName("added_on")
	val addedOn: String? = null,

	@field:SerializedName("file")
	val file: List<String?>? = null,

	@field:SerializedName("judul")
	val judul: String? = null
) : Parcelable
