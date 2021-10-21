package com.surelabsid.lti.penilaiankaryawan.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseUser(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data_user")
	val dataUser: DataUser? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class DataUser(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("userid")
	val userid: String? = null
) : Parcelable
