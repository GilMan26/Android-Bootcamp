package com.example.memories.Login

import android.text.TextUtils
import android.util.Patterns
import com.example.memories.Firebase.LoginHelper
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser

class LoginPresenter(val loginView: ILoginContract.ILoginView) : ILoginContract.ILoginPresenter {

    override fun requestLogin(username: String, password: String) {
        if (TextUtils.isEmpty(username)) {
            loginView.showValidationError("User name cannot be empty")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            loginView.showValidationError("Invalid Username")
        } else if (TextUtils.isEmpty(password)) {
            loginView.showValidationError("Password cannot be empty")
        } else if (password.length < 6) {
            loginView.showValidationError("Password length too short")
        } else {
            loginView.showProgress()
            LoginHelper.login(username, password, object : LoginHelper.OnLoginListener {
                override fun onLoginSuccess(user: FirebaseUser?) {
                    loginView.hideProgress()
                    loginView.loginSuccessful()
                }

                override fun onLoginFailure() {
                    loginView.hideProgress()
                    loginView.showLoginError("error")
                }
            })
        }
    }

    override fun requstGoogleLogin(googleSignInAccount: GoogleSignInAccount?) {
        LoginHelper.firebaseAuthWithGoogle(googleSignInAccount)
    }

}