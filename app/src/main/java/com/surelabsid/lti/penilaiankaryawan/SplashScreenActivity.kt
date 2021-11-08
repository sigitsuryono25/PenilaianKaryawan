package com.surelabsid.lti.penilaiankaryawan

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.window.SplashScreenView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.google.firebase.messaging.FirebaseMessaging
import com.pixplicity.easyprefs.library.Prefs
import com.surelabsid.lti.penilaiankaryawan.login.LoginActivity
import com.surelabsid.lti.penilaiankaryawan.utils.Constant

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {


    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_splash_screen)

        FirebaseMessaging.getInstance().subscribeToTopic("pengumuman").addOnSuccessListener {
            Log.d(SplashScreenActivity::class.java.simpleName, "onCreate: success to subscribe topic")
        }


        handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            this.checkSession()
        }, 3000)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacksAndMessages(null)
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