package com.example.memories.login

import com.example.memories.IBaseView
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface ISignupContract {


    interface ISignUpView:IBaseView {

        override fun hideProgress()

        override fun showProgress()

        fun showValidationError(error: String)

        fun showSignupError(error: String)

        fun loginSuccessful()

        fun googleLogin()

        fun requestUserDetails(id:String)


    }


    interface ISignupPresenter {
        fun requestSignup(username: String, password: String)

        fun requstGoogleLogin(googleSignInAccount: GoogleSignInAccount?)

        fun getUserDetails(id:String)

    }


}