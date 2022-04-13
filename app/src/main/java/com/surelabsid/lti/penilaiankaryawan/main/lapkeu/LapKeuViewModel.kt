package com.surelabsid.lti.penilaiankaryawan.main.lapkeu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.surelabsid.lti.penilaiankaryawan.base.BaseViewModel
import com.surelabsid.lti.penilaiankaryawan.model.RequestLapKeu
import com.surelabsid.lti.penilaiankaryawan.response.ResponseDataNPF
import com.surelabsid.lti.penilaiankaryawan.response.ResponseDataNeraca
import kotlinx.coroutines.launch

class LapKeuViewModel : BaseViewModel() {


    private val _dataNeraca = MutableLiveData<ResponseDataNeraca>()
    val dataNeraca: LiveData<ResponseDataNeraca> get() = _dataNeraca

    private val _dataNpf = MutableLiveData<ResponseDataNPF>()
    val dataNpf: LiveData<ResponseDataNPF> get() = _dataNpf


    private val _dataNoa = MutableLiveData<ResponseDataNPF>()
    val dataNoa: LiveData<ResponseDataNPF> get() = _dataNoa

    val error: LiveData<Throwable> get() = _error

    fun getDataNeraca(requestLapKeu: RequestLapKeu?) {
        viewModelScope.launch {
            try {
                val res = apiServiceLapKeu.getDataNeraca(requestLapKeu)
                _dataNeraca.postValue(res)
            } catch (e: Exception) {
                _error.postValue(e)
            }
        }
    }

    fun getDataNpf(requestLapKeu: RequestLapKeu?) {
        viewModelScope.launch {
            try {
                val res = apiServiceLapKeu.getDataNpf(requestLapKeu)
                _dataNpf.postValue(res)
            } catch (e: Exception) {
                _error.postValue(e)
            }
        }
    }
}