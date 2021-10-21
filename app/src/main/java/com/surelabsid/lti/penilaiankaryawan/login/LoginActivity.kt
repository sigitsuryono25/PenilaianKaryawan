package com.surelabsid.lti.penilaiankaryawan.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.pixplicity.easyprefs.library.Prefs
import com.surelabsid.lti.penilaiankaryawan.MainActivity
import com.surelabsid.lti.penilaiankaryawan.R
import com.surelabsid.lti.penilaiankaryawan.databinding.ActivityLoginBinding
import com.surelabsid.lti.penilaiankaryawan.model.User
import com.surelabsid.lti.penilaiankaryawan.response.ResponseUser
import com.surelabsid.lti.penilaiankaryawan.utils.Constant
import es.dmoral.toasty.Toasty

class LoginActivity : AppCompatActivity() {

    private lateinit var vm: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        vm = ViewModelProvider(this).get(LoginViewModel::class.java)

        vm.user.observe(this) {
            Thread.sleep(2000)
            setSuccess(it)
        }

        vm.error.observe(this) {
            Toasty.error(this@LoginActivity, it.message.toString()).show()
        }
        vm.isEnable.observe(this) {
            binding.loginBtn.isEnabled = it
            if (it) {
                binding.loginBtn.text = getString(R.string.masuk)
            } else {
                binding.loginBtn.text = getString(R.string.mengirimkan_data)
            }
        }

        binding.loginBtn.setOnClickListener {
            val user = User()
            user.password = binding.password.text.toString()
            user.userid = binding.userid.text.toString()
            vm.doAuth(user)
        }
    }

    private fun setSuccess(user: ResponseUser) {
        Prefs.putString(Constant.USERID, user.dataUser?.userid)
        Prefs.putString(Constant.NAMA, user.dataUser?.nama)

        Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
        }
        finish()
    }
}