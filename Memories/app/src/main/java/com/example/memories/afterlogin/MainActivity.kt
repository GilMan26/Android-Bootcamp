package com.example.memories.afterlogin

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.util.Log
import android.widget.Toolbar
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
//        binding.mainToolbar.title="Memories"
//        setSupportActionBar(binding.mainToolbar)
//        binding.mainToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
//        binding.mainToolbar.setNavigationOnClickListener{
//            Log.d("activity", "back clicked")
//            supportFragmentManager.popBackStack()
//        }
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