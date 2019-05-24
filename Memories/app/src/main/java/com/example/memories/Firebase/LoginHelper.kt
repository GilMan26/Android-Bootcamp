package com.example.memories.Firebase

import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object LoginHelper {

    lateinit var auth: FirebaseAuth
    lateinit var gso: GoogleSignInOptions

    interface OnSignupListener {
        fun onSignupSuccess(user: FirebaseUser?)
        fun onSignupFaliure()
    }

    interface OnLoginListener {
        fun onLoginSuccess(user: FirebaseUser?)
        fun onLoginFailure()
    }

    fun signUp(username: String, password: String, signupListener: OnSignupListener) {
        auth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(OnCompleteListener {
                    if (it.isSuccessful) {
                        signupListener.onSignupSuccess(auth.currentUser)
                        it.result?.user?.sendEmailVerification()
                    } else {
                        signupListener.onSignupFaliure()
                    }
                })
    }

    fun login(username: String, password: String, loginListener: OnLoginListener) {
        auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(OnCompleteListener {
                    if (it.isSuccessful) {
                        loginListener.onLoginSuccess(auth.currentUser)

                    } else {
                        loginListener.onLoginFailure()
                    }
                })
    }


}