package com.example.memories.login

import android.Manifest
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import com.example.memories.BaseActivity
import com.example.memories.afterlogin.MainActivity
import com.example.memories.repository.LoginHelper.auth
import com.example.memories.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseUser
import android.Manifest.permission
import android.Manifest.permission.READ_CONTACTS
import butterknife.internal.Utils.arrayOf
import android.R




class LoginActivity : BaseActivity() {

    lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, com.example.memories.R.layout.activity_login)
        val loginFragment = LoginFragment()
        supportFragmentManager.beginTransaction().add(com.example.memories.R.id.loginFrame, loginFragment).commitAllowingStateLoss()
    }


    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null)
            updateUI(currentUser)
    }

    fun updateUI(user: FirebaseUser?) {
        var intent = Intent(this, MainActivity::class.java)
        intent.putExtra("user", user)
        intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
        Log.d("user", user.toString())
        startActivity(intent)
        this.finish()
    }

}
