package com.surelabsid.lti.penilaiankaryawan

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.pixplicity.easyprefs.library.Prefs
import com.surelabsid.lti.penilaiankaryawan.login.LoginActivity
import com.surelabsid.lti.penilaiankaryawan.utils.Constant

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

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