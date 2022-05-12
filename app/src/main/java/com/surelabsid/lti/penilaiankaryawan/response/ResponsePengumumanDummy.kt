package com.surelabsid.lti.penilaiankaryawan.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponsePengumumanDummy(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("error")
	val error: Int? = null
) : Parcelable

@Parcelize
data class DataItem(

	@field:SerializedName("post_on")
	val postOn: String? = null,

	@field:SerializedName("jdl_news")
	val jdlNews: String? = null,

	@field:SerializedName("judul_seo")
	val judulSeo: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("nama_kategori")
	val namaKategori: String? = null,

	@field:SerializedName("foto_news")
	val fotoNews: String? = null
) : Parcelable
