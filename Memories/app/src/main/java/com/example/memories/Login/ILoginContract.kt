package com.example.memories.Login

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient

interface ILoginContract {

    interface ILoginView {

        fun showProgress()

        fun hideProgress()

        fun showValidationError(error:String)

        fun showLoginError(string: String)

        fun loginSuccessful()

        fun googleLogin()



    }


    interface ILoginPresenter {
        fun requestLogin(username: String, password: String)

        fun requstGoogleLogin(googleSignInAccount: GoogleSignInAccount?)

    }
}