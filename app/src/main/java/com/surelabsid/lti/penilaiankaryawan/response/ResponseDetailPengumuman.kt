package com.surelabsid.lti.penilaiankaryawan.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseDetailPengumuman(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("data_pengumuman")
	val dataPengumuman: DataPengumuman? = null
) : Parcelable

@Parcelize
data class DataPengumuman(

	@field:SerializedName("keterangan")
	val keterangan: String? = null,

	@field:SerializedName("file")
	val file: List<String?>? = null,

	@field:SerializedName("added_by")
	val addedBy: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("judul")
	val judul: String? = null,

	@field:SerializedName("added_on")
	val addedOn: String? = null
) : Parcelable
