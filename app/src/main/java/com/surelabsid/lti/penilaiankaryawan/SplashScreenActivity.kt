package com.surelabsid.lti.penilaiankaryawan

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.window.SplashScreenView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.google.firebase.messaging.FirebaseMessaging
import com.pixplicity.easyprefs.library.Prefs
import com.surelabsid.lti.penilaiankaryawan.login.LoginActivity
import com.surelabsid.lti.penilaiankaryawan.network.NetworkModule
import com.surelabsid.lti.penilaiankaryawan.utils.Constant
import kotlinx.coroutines.*
import timber.log.Timber
import java.lang.Exception

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {


    private lateinit var handler: Handler
    private lateinit var job: Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_splash_screen)

        FirebaseMessaging.getInstance().subscribeToTopic("pengumuman").addOnSuccessListener {
            Timber
                .tag(SplashScreenActivity::class.java.simpleName)
                .d("onCreate: success to subscribe topic")
        }

        startJob()
    }

    private fun startJob() {
        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val settings = NetworkModule.getService().getSettings()
                delay(3000)
                MainScope().launch {
                    if (settings.code == 200) {
                        Prefs.putString(Constant.URL_PRESENSI, settings.settings?.endPointPresensi)
                        checkSession()
                    }
                }
            } catch (e: Throwable) {
                e.printStackTrace()
                MainScope().launch {
                    AlertDialog.Builder(this@SplashScreenActivity)
                        .setMessage(e.message)
                        .setTitle("Kesalahan")
                        .setPositiveButton("Coba lagi") { _, _ ->
                            startJob()
                        }
                        .setNegativeButton("Tutup") { _, _ ->
                            finishAffinity()
                        }
                        .create().show()
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        job.cancel()
    }

    private fun checkSession() {
        if (Prefs.contains(Constant.USERID)) {
            Intent(this, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(this)
            }
        } else {
            Intent(this, LoginActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(this)
            }
        }

        finish()
    }
}