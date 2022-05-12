package com.surelabsid.lti.penilaiankaryawan.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseSettings(

	@field:SerializedName("settings")
	val settings: Settings? = null,

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class Settings(

	@field:SerializedName("end_point_presensi")
	val endPointPresensi: String? = null,

	@field:SerializedName("id")
	val id: String? = null
) : Parcelable
