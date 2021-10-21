package com.surelabsid.lti.penilaiankaryawan

import android.app.Application
import android.content.ContextWrapper
import com.pixplicity.easyprefs.library.Prefs

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Prefs.Builder()
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setUseDefaultSharedPreference(true)
            .setContext(baseContext)
            .build()
    }
}