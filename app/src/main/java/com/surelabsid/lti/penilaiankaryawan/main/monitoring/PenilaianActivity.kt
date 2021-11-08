package com.surelabsid.lti.penilaiankaryawan.main.monitoring

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.pixplicity.easyprefs.library.Prefs
import com.surelabsid.lti.penilaiankaryawan.R
import com.surelabsid.lti.penilaiankaryawan.utils.Constant

class PenilaianActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_penilaian)

        if (Prefs.getString(Constant.JABATAN).equals("7")) {
            changeFragment(MonitoringFragment(), "Hasil Penilaian Anda")
        } else {
            changeFragment(MonitoringFragment(), "Monitoring Hasil Penilaian")
        }
    }

    private fun changeFragment(fragment: Fragment, titleBar: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()

        supportActionBar?.apply {
            title = titleBar
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}