package com.example.memories

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.memories.afterlogin.MainActivity
import com.example.memories.login.LoginActivity


open class BaseActivity : AppCompatActivity(), FragmentTransactionHandler {

    override fun pushFragment(fragment: Fragment) {
        if (this is LoginActivity)
            supportFragmentManager.beginTransaction().replace(R.id.loginFrame, fragment).commitAllowingStateLoss()
        else if (this is MainActivity)
            supportFragmentManager.beginTransaction().replace(R.id.mainFrame, fragment).commitAllowingStateLoss()
    }

}
