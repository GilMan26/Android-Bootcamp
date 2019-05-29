package com.example.memories.AfterLogin

import com.example.memories.IBaseView

interface IProfileContract {

    interface IProfileView: IBaseView {

        fun requestChangeProfile()



    }

    interface IProfilePresenter{

        fun getDetials()

        fun changeProfile()

    }

}