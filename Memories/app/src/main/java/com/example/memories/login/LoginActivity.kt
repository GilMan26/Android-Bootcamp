package com.example.memories.login

import android.Manifest
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import com.example.memories.BaseActivity
import com.example.memories.afterlogin.MainActivity
import com.example.memories.repository.LoginHelper.auth
import com.example.memories.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseUser
import android.support.design.widget.Snackbar
import com.example.memories.NetworkReciever
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat


class LoginActivity : BaseActivity(), NetworkReciever.INetworkStateListener {

    lateinit var binding: ActivityLoginBinding
    val REQUEST_PERMISSIONS_REQUEST_CODE = 991

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
//        if(!checkPermissions()){
//            requestPermissions()
//        }
    }

    fun updateUI(user: FirebaseUser?) {
        var intent = Intent(this, MainActivity::class.java)
        intent.putExtra("user", user)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        Log.d("user", user.toString())
        startActivity(intent)
        this.finish()
    }

    override fun onNetworkStateChange(state: Boolean) {
        if (state)
            Snackbar.make(binding.loginFrame, "Back Online", Snackbar.LENGTH_LONG)
        else
            Snackbar.make(binding.loginFrame, "Network Disconnected", Snackbar.LENGTH_LONG)
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

}
