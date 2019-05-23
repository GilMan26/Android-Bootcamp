package com.example.memories

import android.app.Application
import com.example.memories.Firebase.LoginHelper
import com.google.firebase.auth.FirebaseAuth

class App: Application() {

    lateinit var auth: FirebaseAuth

    override fun onCreate() {
        super.onCreate()
        auth= FirebaseAuth.getInstance()

        LoginHelper.auth = auth

    }


}