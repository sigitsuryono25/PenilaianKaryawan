package com.surelabsid.lti.penilaiankaryawan.main.lapkeu

import android.app.ProgressDialog
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.surelabsid.lti.penilaiankaryawan.databinding.ActivityLaporanTableViewBinding
import com.surelabsid.lti.penilaiankaryawan.model.RequestLapKeu
import com.surelabsid.lti.penilaiankaryawan.network.NetworkModule
import java.net.URLEncoder

class LaporanTableViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLaporanTableViewBinding
    private lateinit var pd: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaporanTableViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pd = ProgressDialog(this)
        pd.setMessage("Memuat halaman...")
        pd.setCancelable(false)
        pd.isIndeterminate = true

        binding.wv.settings.javaScriptEnabled = true
        binding.wv.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                pd.dismiss()
            }
        }

        val requestData = intent.getParcelableExtra<RequestLapKeu>(REQ_LAP_KEU)
        val url = intent.getStringExtra(URL_REQ)
        val title = intent.getStringExtra(TITLE_REQ)

        supportActionBar?.apply {
            setTitle(title)
            setDisplayHomeAsUpEnabled(true)
        }
        pd.show()
        if (requestData != null) {
            val headers = hashMapOf<String, String>()
            headers.put("Device-Terminal", "d3nMas")
            val gson = Gson()
            val param = gson.toJson(requestData)
            val postData = "q=" + URLEncoder.encode(param)
            binding.wv.postUrl("${NetworkModule.DEBUG_URL}$url", postData.toByteArray())
        } else {
            pd.dismiss()
            AlertDialog.Builder(this)
                .setMessage("Tidak ada data yang dikirimkan")
                .setTitle("Kesalahan !")
                .setPositiveButton("Oke") { d, i ->
                    finish()
                }
                .create().show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val REQ_LAP_KEU = "request"
        const val URL_REQ = "urlReq"
        const val TITLE_REQ = "titles"
    }
}