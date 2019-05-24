package com.example.memories

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.memories.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity(), SignUpFragment.ISwitchView {

    lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val signUpFragment = SignUpFragment()
        signUpFragment.setSwitchInstance(this)
        supportFragmentManager.beginTransaction().add(R.id.loginFrame, signUpFragment).commit()
    }

    override fun replaceFragment() {
        Log.d("click", "activity")
        val loginFragment = LoginFragment()

        supportFragmentManager.beginTransaction().replace(R.id.loginFrame, loginFragment).commit()
    }


}
