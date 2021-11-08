package com.surelabsid.lti.penilaiankaryawan.main.pengumuman

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
import com.surelabsid.lti.penilaiankaryawan.R
import com.surelabsid.lti.penilaiankaryawan.databinding.ActivityDetailPengumumanBinding
import com.surelabsid.lti.penilaiankaryawan.response.DataPengumumanItem
import com.surelabsid.lti.penilaiankaryawan.utils.DownloadManagerUtils

class DetailPengumumanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPengumumanBinding
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailPengumumanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
        }

        val itemPengumuman = intent.getParcelableExtra<DataPengumumanItem>("dataPengumuman")

        binding.judulPengumuman.text = itemPengumuman?.judul
        binding.isipengumuman.loadData(itemPengumuman?.keterangan.toString(), "text/html", "utf-8")

        val chipGroup = ChipGroup(this)
        val lParamsChipGroup = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        chipGroup.layoutParams = lParamsChipGroup

        if (itemPengumuman?.file?.isEmpty() == true)
            binding.scrollLampiran.visibility = View.GONE


        itemPengumuman?.file?.forEachIndexed { i, s ->
            val chip = Chip(this)
            val lParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            chip.setChipBackgroundColorResource(R.color.blue_200)
            chip.layoutParams = lParams
            chip.text = String.format("Lampiran %d", i.plus(1))
            val chipDrawable = ChipDrawable.createFromResource(this, R.xml.chip)
            chipDrawable.chipBackgroundColor = ActivityCompat.getColorStateList(this, R.color.blue_200)
            chip.chipIcon = chipDrawable

            chipGroup.addView(chip)

            url = s

            chip.setOnClickListener {
                checkPermission()
            }
        }

        binding.containerLampriran.addView(chipGroup, -1)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun lauchDownloader(uri: String) {
        DownloadManagerUtils.instance(
            this@DetailPengumumanActivity,
            uri
        )
    }

    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_DENIED
        ) {
            requestPermissions(
                arrayOf(
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ), 1001
            )
        } else {
            lauchDownloader(url.toString())
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1001) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                lauchDownloader(url.toString())
            }
        }
    }
}