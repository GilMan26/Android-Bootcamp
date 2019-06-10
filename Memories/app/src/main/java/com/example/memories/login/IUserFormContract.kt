package com.example.memories.login

import android.graphics.Bitmap

interface IUserFormContract {

    interface IUserFormView{
        fun getUser(name:String, bitmap: Bitmap)


    }


    interface IUserFormPresenter{

        fun saveUser(name: String, bitmap: Bitmap)

    }
}