package com.example.memories.Login

interface ISignupContract {


    interface ISignUpView {

        fun showProgress()

        fun showValidationError(error: String)

        fun showSignupError(error: String)

        fun loginSuccessful()

        fun hideProgress()
    }


    interface ISignupPresenter {
        fun requestSignup(username: String, password: String)
    }


}