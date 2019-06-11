package com.example.memories.login

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.memories.BaseActivity
import com.example.memories.BaseFragment
import com.example.memories.afterlogin.MainActivity
import com.example.memories.repository.LoginHelper.auth
import com.example.memories.R
import com.example.memories.databinding.ActivityLoginBinding
import com.example.memories.repository.LoginHelper.login
import com.google.firebase.auth.FirebaseUser


class LoginActivity : BaseActivity() {

    lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val loginFragment = LoginFragment()
        supportFragmentManager.beginTransaction().add(R.id.loginFrame, loginFragment).commitAllowingStateLoss()
    }


    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null)
            updateUI(currentUser)
    }

    fun updateUI(user: FirebaseUser?) {
        var intent = Intent(this, MainActivity::class.java)
        intent.putExtra("user", user)
        Log.d("user", user.toString())
        startActivity(intent)
    }

}
