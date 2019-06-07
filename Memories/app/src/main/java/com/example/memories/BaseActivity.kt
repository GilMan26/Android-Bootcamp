package com.example.memories

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity


open class BaseActivity : AppCompatActivity(), FragmentTransactionHandler {

    override fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.mainFrame, fragment).commitAllowingStateLoss()
    }

}
