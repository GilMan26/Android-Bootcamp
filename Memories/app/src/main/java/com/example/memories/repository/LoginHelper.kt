package com.example.memories.repository

import android.util.Log
import com.example.memories.repository.LoginHelper.database
import com.example.memories.repository.LoginHelper.firebaseUser
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
        fun onSignupSuccess(firebaseuser: FirebaseUser?)
        fun onSignupFaliure()
    }

    interface OnLoginListener {
        fun onLoginSuccess(user: FirebaseUser?)
        fun onLoginFailure()
    }

    interface OnGoogleSignIn {

        fun onSuccess(firebaseuser: FirebaseUser?)

        fun onFailure(ac: String)
    }

    interface SignOutListener {

        fun onSignout()

    }

    fun signUp(username: String, password: String, signupListener: OnSignupListener) {
        auth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(OnCompleteListener {
                    if (it.isSuccessful) {
                        it.result?.user?.sendEmailVerification()
                        Log.d("signup", "success")
                        signupListener.onSignupSuccess(firebaseuser = auth.currentUser)


                    } else {
                        Log.d("signup", "failed")
                        signupListener.onSignupFaliure()
                    }
                })
    }

    fun login(username: String, password: String, loginListener: OnLoginListener) {
        auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(OnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d("login", "success")
                        loginListener.onLoginSuccess(auth.currentUser)

                    } else {
                        Log.d("login", "failure")
                        loginListener.onLoginFailure()
                    }
                })
    }


    fun firebaseAuthWithGoogle(acct: GoogleSignInAccount?, iGoogleSignIn: OnGoogleSignIn) {
        Log.d("google model", "firebaseAuthWithGoogle:")

        val credential = GoogleAuthProvider.getCredential(acct?.idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(OnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d("google", acct?.displayName + acct?.photoUrl)
                        var data = User(auth.currentUser!!.uid, acct?.displayName.toString(), acct?.photoUrl.toString())
//                        saveUserDb(data, auth.currentUser)
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("google", "signInWithCredential:success")
                        val user = auth.currentUser
                        iGoogleSignIn.onSuccess(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("google", "signInWithCredential:failure")
                        iGoogleSignIn.onFailure("failed")
                    }
                })

    }

    fun signOut(signOutListener: SignOutListener) {
        auth.signOut()
        signOutListener.onSignout()

    }

    fun saveUserDb(user: User) {
        Log.d("db", firebaseUser.uid)
        val userRef = database.getReference("/users")
        userRef.child(firebaseUser.uid).setValue(user)
    }

}