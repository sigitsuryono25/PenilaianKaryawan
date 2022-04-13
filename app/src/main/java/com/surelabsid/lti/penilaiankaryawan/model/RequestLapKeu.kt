package com.surelabsid.lti.penilaiankaryawan.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RequestLapKeu(
    var request: String,
    var userid: String? = "system",
    var signature: String? = "d3nm@sDevtern",
    var data01: DataParam,
): Parcelable
@Parcelize

data class DataParam(
    var tgl: String? = null,
    var tgl1: String? = null,
    var tgl2: String? = null,
    var golac: String? = null,
    var kdloc: String = "01",
    var kode: String? = null
): Parcelable