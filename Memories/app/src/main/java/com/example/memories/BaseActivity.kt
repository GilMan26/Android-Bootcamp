package com.example.memories

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.memories.afterlogin.MainActivity
import com.example.memories.login.LoginActivity


open class BaseActivity : AppCompatActivity(), FragmentTransactionHandler {

    override fun pushFragment(fragment: Fragment) {
        if (this is LoginActivity){
            supportFragmentManager.beginTransaction().setTransition(4099).replace(R.id.loginFrame, fragment).addToBackStack(fragment.toString()).commitAllowingStateLoss()

        }
        else if (this is MainActivity)
            supportFragmentManager.beginTransaction().setTransition(4099).replace(R.id.mainFrame, fragment).addToBackStack(fragment.toString()).commitAllowingStateLoss()
    }

    override fun pushFullFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().setTransition(4099).replace(R.id.fullFrame, fragment).addToBackStack(fragment.toString()).commitAllowingStateLoss()
    }

}
