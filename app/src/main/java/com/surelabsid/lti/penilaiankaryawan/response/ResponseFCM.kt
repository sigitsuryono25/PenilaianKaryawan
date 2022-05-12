package com.surelabsid.lti.penilaiankaryawan.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseFCM(

	@field:SerializedName("dari")
	val dari: String? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable
