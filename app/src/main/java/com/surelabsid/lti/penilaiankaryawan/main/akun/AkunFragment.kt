package com.surelabsid.lti.penilaiankaryawan.main.akun

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.surelabsid.lti.penilaiankaryawan.R
import com.surelabsid.lti.penilaiankaryawan.databinding.FragmentAkunBinding
import es.dmoral.toasty.Toasty


class AkunFragment : Fragment(R.layout.fragment_akun) {

    private lateinit var binding: FragmentAkunBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAkunBinding.bind(view)


        binding.logout.setOnClickListener {
            Toasty.success(requireActivity(), "Logout").show()
        }
    }
}