package com.example.memories.Login

import android.text.TextUtils
import android.util.Patterns
import com.example.memories.Firebase.LoginHelper
import com.google.firebase.auth.FirebaseUser

class SignUpPresenter(val signUpView: ISignupContract.ISignUpView) : ISignupContract.ISignupPresenter {

    override fun requestSignup(username: String, password: String) {
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
            LoginHelper.signUp(username, password, object : LoginHelper.OnSignupListener {
                override fun onSignupSuccess(user: FirebaseUser?) {
                    signUpView.hideProgress()
                    signUpView.loginSuccessful()
                }

                override fun onSignupFaliure() {
                    signUpView.hideProgress()
                    signUpView.showSignupError("error")
                }
            })
        }
    }


}