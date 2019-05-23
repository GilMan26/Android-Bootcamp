package com.example.memories

interface ILoginContract {

    interface ILoginView {

        fun showProgress()

        fun hideProgress()

        fun showValidationError(error:String)

        fun showLoginError(string: String)

        fun loginSuccessful()

    }


    interface ILoginPresenter {
        fun requestLogin(username: String, password: String)
    }
}