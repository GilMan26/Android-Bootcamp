package com.example.memories.login

import android.graphics.Bitmap
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import com.example.memories.repository.DataManager
import com.example.memories.repository.LoginHelper
import com.example.memories.repository.User
import com.google.firebase.auth.FirebaseUser

class SignUpPresenter(val signUpView: ISignupContract.ISignUpView) : ISignupContract.ISignupPresenter {

    override fun requestSignup(username: String, password: String,name:String, bitmap: Bitmap) {
        if (TextUtils.isEmpty(username)) {
            signUpView.showValidationError("User name cannot be empty")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            signUpView.showValidationError("Invalid Username")
        } else if (TextUtils.isEmpty(password)) {
            signUpView.showValidationError("Password cannot be empty")
        } else if (password.length < 6) {
            signUpView.showValidationError("Password length too short")
        } else {
            signUpView.showProgress()
            DataManager.uploadImage(name, bitmap, object: DataManager.IImageUploadCallback{
                override fun onSuccess(downloadUrl: String) {
                    Log.d("presenter", downloadUrl)
                    LoginHelper.signUp(username, password, object : LoginHelper.OnSignupListener{

                        override fun onSignupSuccess(firebaseuser: FirebaseUser?) {
                            if(firebaseuser!=null){
                                LoginHelper.saveUserDb(User(firebaseuser.uid, name, downloadUrl))
//                                DataManager.getUser(object : DataManager.IUserDataCallback{
//                                    override fun onSuccess(user: User) {
//
//                                        Log.d("userdata", "success")
//                                    }
//
//                                    override fun onFailure(ack: String) {
//                                        Log.d("userdata", "no success")
//                                    }
//                                })

                            }
                        }

                        override fun onSignupFaliure() {
                            Log.d("signup","failure")
                        }
                    })
                }


                override fun onFailure(ack: String) {
                    Log.d("signup", ack)
                }
            })

        }
    }




}