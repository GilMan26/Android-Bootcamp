package com.example.memories.AfterLogin

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.memories.AfterLogin.AlbumTab.AddAlbumFragment
import com.example.memories.AfterLogin.AlbumTab.AlbumListFragment
import com.example.memories.AfterLogin.ProfileTab.ProfileFragment
import com.example.memories.AfterLogin.TimelineTab.TimelineFragment
import com.example.memories.R
import com.example.memories.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity(), IActivityInteractor {


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
        albumListFragment.setInstance(this)
        supportFragmentManager.beginTransaction().add(R.id.mainFrame, albumListFragment)

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
    override fun addAlbum() {
        var addAlbum=AddAlbumFragment()
        Log.d("shit", "here")
        supportFragmentManager.beginTransaction().add(R.id.mainFrame, addAlbum).commit()
    }


}