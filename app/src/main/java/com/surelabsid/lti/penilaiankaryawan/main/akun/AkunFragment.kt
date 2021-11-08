package com.surelabsid.lti.penilaiankaryawan.main.akun

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.pixplicity.easyprefs.library.Prefs
import com.surelabsid.lti.penilaiankaryawan.R
import com.surelabsid.lti.penilaiankaryawan.databinding.FragmentAkunBinding
import com.surelabsid.lti.penilaiankaryawan.login.LoginActivity
import com.surelabsid.lti.penilaiankaryawan.utils.Constant
import es.dmoral.toasty.Toasty


class AkunFragment : Fragment(R.layout.fragment_akun) {

    private lateinit var binding: FragmentAkunBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAkunBinding.bind(view)

        binding.nama.text = Prefs.getString(Constant.NAMA).trim()
        binding.userid.text = Prefs.getString(Constant.USERID).trim()


        binding.logout.setOnClickListener {
            AlertDialog.Builder(requireActivity())
                .setTitle("Konfirmasi")
                .setMessage("Semua sesi akan diakhiri dan anda harus login kembali.\nLanjutkan?")
                .setPositiveButton(
                    "Ok"
                ) { _, _ ->
                    Prefs.clear()
                    ActivityCompat.finishAffinity(requireActivity())
                    Intent(requireActivity(), LoginActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(this)
                    }
                }
                .setNegativeButton("Batal") { d, _ ->
                    d.dismiss()
                }.create().show()
        }
    }
}