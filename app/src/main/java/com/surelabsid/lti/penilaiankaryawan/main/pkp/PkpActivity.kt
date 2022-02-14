package com.surelabsid.lti.penilaiankaryawan.main.pkp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.pixplicity.easyprefs.library.Prefs
import com.surelabsid.lti.penilaiankaryawan.R
import com.surelabsid.lti.penilaiankaryawan.WebViewActivity
import com.surelabsid.lti.penilaiankaryawan.databinding.ActivityPkpBinding
import com.surelabsid.lti.penilaiankaryawan.main.pkp.ui.PilihKaryawan
import com.surelabsid.lti.penilaiankaryawan.utils.Constant

class PkpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPkpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPkpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.containerPkp, PilihKaryawan())
            .commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_penilaian, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.presensi -> {
                if(Prefs.contains(Constant.IS_SELECTED_KAR) && Prefs.getBoolean(Constant.IS_SELECTED_KAR)){
                    Intent(this, WebViewActivity::class.java).apply {
                        putExtra("url", Prefs.getString(Constant.URL_PRESENSI))
                        startActivity(this)
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}