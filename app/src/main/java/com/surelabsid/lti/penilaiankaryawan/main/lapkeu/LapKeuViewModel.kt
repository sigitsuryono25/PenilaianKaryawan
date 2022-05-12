package com.surelabsid.lti.penilaiankaryawan.main.lapkeu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.surelabsid.lti.penilaiankaryawan.base.BaseViewModel
import com.surelabsid.lti.penilaiankaryawan.model.RequestLapKeu
import com.surelabsid.lti.penilaiankaryawan.response.ResponseListKantor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LapKeuViewModel : BaseViewModel() {


    private val _listKantor = MutableLiveData<ResponseListKantor>()
    val dataKantor: LiveData<ResponseListKantor> get() = _listKantor

    val error : LiveData<Throwable> get() = _error


    fun getListKantor(requestLapKeu: RequestLapKeu) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = apiServiceLapKeu.getListKantor(requestLapKeu)
                _listKantor.postValue(data)
            } catch (e: Throwable) {
                _error.postValue(e)
            }
        }

    }

}