package com.surelabsid.lti.penilaiankaryawan.main.lapkeu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.surelabsid.lti.penilaiankaryawan.databinding.ActivityListKantorBinding
import com.surelabsid.lti.penilaiankaryawan.main.lapkeu.adapter.AdapterListKantor
import com.surelabsid.lti.penilaiankaryawan.model.DataParam
import com.surelabsid.lti.penilaiankaryawan.model.RequestLapKeu
import com.surelabsid.lti.penilaiankaryawan.response.ResponseListKantor
import es.dmoral.toasty.Toasty

class ListKantorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListKantorBinding
    private lateinit var adapterListKantor: AdapterListKantor
    private lateinit var lapKeuViewModel: LapKeuViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListKantorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Daftar Kantor"
            setDisplayHomeAsUpEnabled(true)
        }

        lapKeuViewModel = ViewModelProvider(this)[LapKeuViewModel::class.java]

        adapterListKantor = AdapterListKantor {
            val iii = Intent()
            iii.putExtra(SELECTED_RESULT, it)
            setResult(Activity.RESULT_OK, iii)
            finish()
        }
        binding.listKantor.apply {
            adapter = adapterListKantor
            layoutManager = LinearLayoutManager(this@ListKantorActivity)
        }

        val requestLapKeu = RequestLapKeu(
            request = "kantor", data01 = DataParam()
        )
        lapKeuViewModel.getListKantor(requestLapKeu)
        lapKeuViewModel.dataKantor.observe(this) {
            updateUI(it)
        }
        lapKeuViewModel.error.observe(this) {
            updateError(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateError(throwable: Throwable?) {
        Toasty.error(this, throwable?.message.toString()).show()
    }

    private fun updateUI(responseListKantor: ResponseListKantor?) {
        if (responseListKantor?.rcode == "000") {
            responseListKantor.data01?.let { adapterListKantor.addItem(it) }
        } else {
            Toasty.error(this, responseListKantor?.msg.toString()).show()
        }
    }

    companion object {
        const val SELECTED_RESULT = "result"
    }
}