package com.surelabsid.lti.penilaiankaryawan.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.surelabsid.lti.penilaiankaryawan.network.NetworkModule

open class BaseViewModel: ViewModel() {
    val apiService = NetworkModule.getService()
    val apiServiceLapKeu = NetworkModule.getServiceLapKeu()
    var _error = MutableLiveData<Throwable>()
}