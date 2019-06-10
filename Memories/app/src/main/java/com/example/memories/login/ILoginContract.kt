package com.example.memories.login

import com.example.memories.IBaseView
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface ILoginContract {

    interface ILoginView:IBaseView {

        override fun hideProgress()

        override fun showProgress()

        fun showValidationError(error:String)

        fun showLoginError(string: String)

        fun loginSuccessful()




    }


    interface ILoginPresenter {
        fun requestLogin(username: String, password: String)


    }
}