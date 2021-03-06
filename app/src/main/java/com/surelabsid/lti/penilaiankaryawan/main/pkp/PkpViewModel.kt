package com.surelabsid.lti.penilaiankaryawan.main.pkp

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.surelabsid.lti.penilaiankaryawan.base.BaseViewModel
import com.surelabsid.lti.penilaiankaryawan.model.PenilaianSend
import com.surelabsid.lti.penilaiankaryawan.response.GeneralResponse
import com.surelabsid.lti.penilaiankaryawan.response.ResponseJabatan
import com.surelabsid.lti.penilaiankaryawan.response.ResponseKaryawan
import com.surelabsid.lti.penilaiankaryawan.response.ResponseParams
import com.surelabsid.lti.penilaiankaryawan.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class PkpViewModel : BaseViewModel() {

    private val _data = MutableLiveData<ResponseKaryawan>()
    val data: LiveData<ResponseKaryawan> get() = _data

    private val _generalRes = SingleLiveEvent<GeneralResponse>()
    val generalRes: LiveData<GeneralResponse> get() = _generalRes

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

    fun getJabatan(level: String?){
        viewModelScope.launch {
            try {
                val data = apiService.getJabatan(level)
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

    fun sendPenilaian(penilaianSend: PenilaianSend){
        viewModelScope.launch {
            try {
                val data = apiService.sendPenilaian(penilaianSend)
                _generalRes.postValue(data)
            }catch(e: Throwable){
                e.printStackTrace()
                _error.postValue(e)
                _isLoading.postValue(View.GONE)
            }
        }
    }

}