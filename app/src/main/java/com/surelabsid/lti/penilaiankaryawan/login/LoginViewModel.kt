package com.surelabsid.lti.penilaiankaryawan.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.surelabsid.lti.penilaiankaryawan.base.BaseViewModel
import com.surelabsid.lti.penilaiankaryawan.model.User
import com.surelabsid.lti.penilaiankaryawan.response.ResponseUser
import com.surelabsid.lti.penilaiankaryawan.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {

    private var _user = SingleLiveEvent<ResponseUser>()
    val user: LiveData<ResponseUser> get() = _user
    val error: LiveData<Throwable> get() = _error

    private var _isEnable = SingleLiveEvent<Boolean>()
    val isEnable: LiveData<Boolean> get() = _isEnable

    fun doAuth(user: User) {
        _isEnable.postValue(false)
        viewModelScope.launch {
            try {
                val data = apiService.auth(user)
                _user.postValue(data)
                _isEnable.postValue(true)
            } catch (t: Throwable) {
                t.printStackTrace()
                _error.postValue(t)
                _isEnable.postValue(true)
            }
        }
    }
}