package com.example.memories.Login

import com.example.memories.IBaseView
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient

interface ILoginContract {

    interface ILoginView:IBaseView {

        override fun hideProgress()

        override fun showProgress()

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