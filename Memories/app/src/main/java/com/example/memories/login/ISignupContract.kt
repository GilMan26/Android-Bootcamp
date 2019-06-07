package com.example.memories.login

import com.example.memories.IBaseView

interface ISignupContract {


    interface ISignUpView:IBaseView {

        override fun hideProgress()

        override fun showProgress()

        fun showValidationError(error: String)

        fun showSignupError(error: String)

        fun loginSuccessful()

    }


    interface ISignupPresenter {
        fun requestSignup(username: String, password: String)
    }


}