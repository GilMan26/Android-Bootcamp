package com.example.memories.afterlogin.album

import android.graphics.Bitmap

interface IAddImage {

    interface IAddImageView {

        fun uploadSuccess()

    }


    interface IAddImagePresenter {

        fun uploadImage(title: String, bitmap: Bitmap, ref: String)
    }

}