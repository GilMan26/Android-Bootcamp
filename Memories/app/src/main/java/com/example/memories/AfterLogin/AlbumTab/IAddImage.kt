package com.example.memories.AfterLogin.AlbumTab

import android.graphics.Bitmap
import com.example.memories.Repository.Photo

interface IAddImage {

    interface IAddImageView{

        fun uploadSuccess()

    }


    interface IAddImagePresenter{

        fun uploadImage(title:String, bitmap: Bitmap)
    }

}