package com.surelabsid.lti.penilaiankaryawan.model

data class Penilaian (
    var pairNilaiXidPoint: MutableList<Nilai>? = null,
    var bobotPoint: String? = null,
    var nilaiAKhirPerPoint: String? = null,
    var idBidang: String? = null
)


data class Nilai(
    var idPoint: String? = null,
    var nilai: Double? = null
)


class PenilaianSend {
    var userid: String? = null
    var penilaian: List<Penilaian>? = null
    var dinilaiOleh: String? = null
}