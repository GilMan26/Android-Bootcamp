package com.example.memories.afterlogin

import android.Manifest
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v7.app.ActionBar
import android.util.Log
import android.widget.Toolbar
import com.example.memories.afterlogin.album.*
import com.example.memories.afterlogin.profile.ProfileFragment
import com.example.memories.afterlogin.timeline.TimelineFragment
import com.example.memories.BaseActivity
import com.example.memories.NetworkReciever
import com.example.memories.R
import com.example.memories.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseUser

class MainActivity : BaseActivity(), ProfileFragment.ITerminator, NetworkReciever.INetworkStateListener {

    lateinit var binding: ActivityMainBinding
    lateinit var firebaseUser: FirebaseUser
    val REQUEST_PERMISSIONS_REQUEST_CODE = 991

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        firebaseUser = intent.getParcelableExtra("user")
//        binding.mainToolbar.title="Memories"
//        setSupportActionBar(binding.mainToolbar)
//        binding.mainToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
//        binding.mainToolbar.setNavigationOnClickListener{
//            Log.d("activity", "back clicked")
//            supportFragmentManager.popBackStack()
//        }
        val albumListFragment = AlbumListFragment()
        val profileFragment = ProfileFragment()
        profileFragment.setInstance(this)
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

    override fun onStart() {
        super.onStart()
//        if(!checkPermissions())
//            requestPermissions()
    }


    private fun checkPermissions(): Boolean {
        val permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
        return permissionState == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {

        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_PERMISSIONS_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.size <= 0) {
                // If user interaction was interrupted, the permission request is cancelled and you
                // receive empty arrays.
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted. Kick off the process of building and connecting
                // GoogleApiClient.
                // perform your operation
            } else {
                // Permission denied.
            }
        }
    }

    override fun onLogout() {
        this.finish()
    }

    override fun onNetworkStateChange(state: Boolean) {
        if (state)
            Snackbar.make(binding.mainFrame, "Back Online", Snackbar.LENGTH_LONG)
        else
            Snackbar.make(binding.mainFrame, "Network Disconnected", Snackbar.LENGTH_LONG)

    }

}