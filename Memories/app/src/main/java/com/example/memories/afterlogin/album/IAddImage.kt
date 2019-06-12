package com.example.memories.afterlogin.album

import android.graphics.Bitmap
import com.example.memories.IBaseView

interface IAddImage {

    interface IAddImageView : IBaseView {

        fun uploadSuccess()

        override fun showProgress()

        override fun hideProgress()


    }


    interface IAddImagePresenter {

        fun uploadImage(title: String, bitmap: Bitmap, ref: String)
    }

}