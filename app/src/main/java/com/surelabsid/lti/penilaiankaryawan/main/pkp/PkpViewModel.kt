package com.surelabsid.lti.penilaiankaryawan.main.pkp

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.surelabsid.lti.penilaiankaryawan.base.BaseViewModel
import com.surelabsid.lti.penilaiankaryawan.response.ResponseJabatan
import com.surelabsid.lti.penilaiankaryawan.response.ResponseKaryawan
import com.surelabsid.lti.penilaiankaryawan.response.ResponseParams
import kotlinx.coroutines.launch

class PkpViewModel : BaseViewModel() {

    private val _data = MutableLiveData<ResponseKaryawan>()
    val data: LiveData<ResponseKaryawan> get() = _data

    private val _dataJabatan = MutableLiveData<ResponseJabatan>()
    val dataJabatan: LiveData<ResponseJabatan> get() = _dataJabatan

    private val _dataParams = MutableLiveData<ResponseParams>()
    val dataParams: LiveData<ResponseParams> get() = _dataParams

    val error: LiveData<Throwable> get() = _error
    private val _isLoading = MutableLiveData<Int>()
    val isLoading: LiveData<Int> get() = _isLoading


    fun getListKaryawan(page: Int, idJabatan: String?) {
        viewModelScope.launch {
            try {
                val data = apiService.getKaryawan(page, idJabatan)
                _data.postValue(data)
                _isLoading.postValue(View.GONE)
            } catch (e: Throwable) {
                _error.postValue(e)
                _isLoading.postValue(View.GONE)
            }
        }

    }

    fun getJabatan(){
        viewModelScope.launch {
            try {
                val data = apiService.getJabatan()
                _dataJabatan.postValue(data)
                _isLoading.postValue(View.GONE)
            } catch (e: Throwable) {
                _error.postValue(e)
                _isLoading.postValue(View.GONE)
            }
        }
    }

    fun getParams(idJabatan: String?){
        viewModelScope.launch {
            try {
                val data = apiService.getParam(idJabatan)
                _dataParams.postValue(data)
                _isLoading.postValue(View.GONE)
            } catch (e: Throwable) {
                _error.postValue(e)
                _isLoading.postValue(View.GONE)
            }
        }
    }

}