package com.example.memories

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.memories.AfterLogin.MainActivity
import com.example.memories.Firebase.LoginHelper.auth
import com.example.memories.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


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

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
//        updateUI(currentUser)
    }

    fun updateUI(user: FirebaseUser?){
        startActivity(Intent(this, MainActivity::class.java))
    }


}
