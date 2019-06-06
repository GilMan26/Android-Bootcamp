package com.example.memories.Repository

import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase

object LoginHelper {

    lateinit var auth: FirebaseAuth
    lateinit var gso: GoogleSignInOptions
    lateinit var database: FirebaseDatabase
    lateinit var firebaseUser: FirebaseUser

    interface OnSignupListener {
        fun onSignupSuccess(user:User, firebaseuser: FirebaseUser?)
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
//                        signupListener.onSignupSuccess(user=User(username), firebaseuser = auth.currentUser)
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

    fun firebaseAuthWithGoogle(acct: GoogleSignInAccount?) {
        Log.d("google model", "firebaseAuthWithGoogle:")

        val credential = GoogleAuthProvider.getCredential(acct?.idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(OnCompleteListener {
                    if (it.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("google", "signInWithCredential:success")
                        val user = auth.currentUser
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("google", "signInWithCredential:failure")
                    }
                })

    }

    fun saveUserDb(user:User, firebaseUser: FirebaseUser?){
        if (firebaseUser != null) {
            Log.d("db", firebaseUser.uid)
            val userRef= database.getReference("/users")
            userRef.child(firebaseUser.uid).setValue(user)
        }
    }

}