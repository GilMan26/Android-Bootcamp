package com.example.memories.AfterLogin.AlbumTab

import android.graphics.Bitmap
import android.util.Log
import com.example.memories.Repository.DataManager

class AddImagePresenter(val iAddImageView: IAddImage.IAddImageView):IAddImage.IAddImagePresenter {

    override fun uploadImage(title: String, bitmap: Bitmap) {
        DataManager.uploadImage(title, bitmap, object:DataManager.IImageUploadCallback{
            override fun onSuccess(downloadUrl: String) {
                Log.d("storage", downloadUrl)
            }

            override fun onFailure(ack: String) {
                Log.d("storage", "failed "+ack)
            }
        })
    }

}