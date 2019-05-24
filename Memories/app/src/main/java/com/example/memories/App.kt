package com.example.memories

import android.app.Application
import com.example.memories.Firebase.LoginHelper
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class App : Application() {

    lateinit var auth: FirebaseAuth
    lateinit var gso: GoogleSignInOptions

    override fun onCreate() {
        super.onCreate()
        auth = FirebaseAuth.getInstance()
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        LoginHelper.auth = auth
        LoginHelper.gso = gso

    }


}