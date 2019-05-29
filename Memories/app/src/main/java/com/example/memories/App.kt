package com.example.memories

import android.app.Application
import android.content.Intent
import com.example.memories.Firebase.LoginHelper
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class App : Application() {

    lateinit var auth: FirebaseAuth
    lateinit var gso: GoogleSignInOptions
    lateinit var googleSignInClient: GoogleSignInClient
    lateinit var database :FirebaseDatabase

    override fun onCreate() {
        super.onCreate()
        auth = FirebaseAuth.getInstance()
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        database= FirebaseDatabase.getInstance()
        googleSignInClient=GoogleSignIn.getClient(this, gso)

        LoginHelper.auth = auth
        LoginHelper.gso = gso
        LoginHelper.database=database

    }



}