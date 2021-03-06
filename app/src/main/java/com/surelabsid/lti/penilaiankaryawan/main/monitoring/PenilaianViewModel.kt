package com.surelabsid.lti.penilaiankaryawan.main.monitoring

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.surelabsid.lti.penilaiankaryawan.base.BaseViewModel
import com.surelabsid.lti.penilaiankaryawan.response.ResponsePenilaian
import kotlinx.coroutines.launch

class PenilaianViewModel: BaseViewModel() {

    private val _data = MutableLiveData<ResponsePenilaian>()
    val data: LiveData<ResponsePenilaian> get() =  _data

    val error: LiveData<Throwable> get() =  _error

    fun getPenilaian(dinilaiOleh: String?, year: Int){
        viewModelScope.launch {
            try {
                val data = apiService.getPenilaian(dinilaiOleh, year)
                _data.postValue(data)
            }catch(e: Throwable){
                _error.postValue(e)
                e.printStackTrace()
            }
        }
    }

    fun getPenilaianByUser(idKaryawan: String?, year: Int){
        viewModelScope.launch {
            try {
                val data = apiService.getPenilaianByUser(idKaryawan, year)
                _data.postValue(data)
            }catch(e: Throwable){
                _error.postValue(e)
                e.printStackTrace()
            }
        }
    }
}