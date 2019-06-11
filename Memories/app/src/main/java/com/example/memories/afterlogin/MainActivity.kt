package com.example.memories.afterlogin

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.memories.afterlogin.album.*
import com.example.memories.afterlogin.profile.ProfileFragment
import com.example.memories.afterlogin.timeline.TimelineFragment
import com.example.memories.BaseActivity
import com.example.memories.R
import com.example.memories.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseUser

class MainActivity : BaseActivity(){


    lateinit var binding: ActivityMainBinding
    lateinit var firebaseUser: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        firebaseUser=intent.getParcelableExtra("user")

        val albumListFragment = AlbumListFragment()
        val profileFragment = ProfileFragment()
        val timelineFragment = TimelineFragment()
        supportFragmentManager.beginTransaction().add(R.id.mainFrame, albumListFragment).commit()

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.categories -> {
                    supportFragmentManager.beginTransaction().replace(R.id.mainFrame, albumListFragment).commit()
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.timeline -> {
                    supportFragmentManager.beginTransaction().replace(R.id.mainFrame, timelineFragment).commit()
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.mainFrame, profileFragment).commit()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }


    }





}