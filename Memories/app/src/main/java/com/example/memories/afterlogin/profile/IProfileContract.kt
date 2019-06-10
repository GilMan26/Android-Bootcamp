package com.example.memories.afterlogin.profile

import com.example.memories.IBaseView

interface IProfileContract {

    interface IProfileView: IBaseView {

        fun requestChangeProfile()



    }

    interface IProfilePresenter{

        fun getDetials()

        fun changeProfile()

        fun logout()

    }

}