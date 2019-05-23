package com.example.memories.Firebase

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object LoginHelper {

    lateinit var auth:FirebaseAuth

    interface OnSignupListener{
        fun onSignupSuccess(user: FirebaseUser?)
        fun onSignupFaliure()
    }

    interface OnLoginListener{
        fun onLoginSuccess(user:FirebaseUser?)
        fun onLoginFailure()
    }

    fun signUp(username:String, password:String, signupListener: OnSignupListener){
        auth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(OnCompleteListener {
                    if (it.isSuccessful){
                        Log.d("auth", "signup successful")
                        signupListener.onSignupSuccess(auth.currentUser)
                        it.result?.user?.sendEmailVerification()
                    }else{
                        signupListener.onSignupFaliure()
                        Log.d("auth", "signup not successful")
                    }
                })
    }

    fun login(username:String, password: String, loginListener: OnLoginListener){
        auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(OnCompleteListener {
                    if(it.isSuccessful){
                        Log.d("auth", "login successful")
                        loginListener.onLoginSuccess(auth.currentUser)
                        Log.d("auth", auth.currentUser.toString())
                    }else{
                        Log.d("auth", "login not successful")
                        loginListener.onLoginFailure()
                    }
                })
    }

}