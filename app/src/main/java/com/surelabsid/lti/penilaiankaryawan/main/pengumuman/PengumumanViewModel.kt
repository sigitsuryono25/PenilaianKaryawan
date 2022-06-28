package com.surelabsid.lti.penilaiankaryawan.main.pengumuman

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.surelabsid.lti.penilaiankaryawan.base.BaseViewModel
import com.surelabsid.lti.penilaiankaryawan.response.ResponsePengumuman
import kotlinx.coroutines.launch

class PengumumanViewModel : BaseViewModel() {

    private val _data = MutableLiveData<ResponsePengumuman>()
    val data: LiveData<ResponsePengumuman> get() = _data

    val error: LiveData<Throwable> get() = _error
    private val _isLoading = MutableLiveData<Int>()
    val isLoading: LiveData<Int> get() = _isLoading


    fun getLatestNews() {
        _isLoading.postValue(View.VISIBLE)
        viewModelScope.launch {
            try {
                val data = apiService.getPengumuman()
                _data.postValue(data)
                _isLoading.postValue(View.GONE)
            } catch (t: Throwable) {
                t.printStackTrace()
                _error.postValue(t)
                _isLoading.postValue(View.GONE)
            }
        }
    }
}