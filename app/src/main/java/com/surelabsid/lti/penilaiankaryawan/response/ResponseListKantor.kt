package com.surelabsid.lti.penilaiankaryawan.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class ResponseListKantor(

    @field:SerializedName("msg")
    val msg: String? = null,

    @field:SerializedName("rcode")
    val rcode: String? = null,

    @field:SerializedName("data01")
    val data01: List<DataKantorItem?>? = null
) : Parcelable

@Parcelize
data class DataKantorItem(

    @field:SerializedName("no")
    val no: String? = null,

    @field:SerializedName("kdktr")
    val kdktr: String? = null,

    @field:SerializedName("nama")
    val nama: String? = null,

    @field:SerializedName("kdloc")
    val kdloc: String? = null,

    @field:SerializedName("kdkas")
    val kdkas: String? = null,

    @field:SerializedName("kdcab")
    val kdcab: String? = null
) : Parcelable
