package com.example.memories.AfterLogin.AlbumTab

import android.graphics.Bitmap
import android.util.Log
import com.example.memories.Repository.DataManager
import com.example.memories.Repository.Photo

class AddImagePresenter(val iAddImageView: IAddImage.IAddImageView) : IAddImage.IAddImagePresenter {

    override fun uploadImage(title: String, bitmap: Bitmap, ref: String) {
        DataManager.uploadImage(title, bitmap, object : DataManager.IImageUploadCallback {
            override fun onSuccess(downloadUrl: String) {
                val tsLong = System.currentTimeMillis() / 1000
                val ts = tsLong.toString()
                var photo = Photo("", title, downloadUrl, ts, "")
                DataManager.addImage(photo, ref, object : DataManager.IImageUploadCallback {
                    override fun onSuccess(downloadUrl: String) {
                        Log.d("upload", "success")
                    }

                    override fun onFailure(ack: String) {
                        Log.d("upload", "failure")
                    }
                })
                Log.d("storage", downloadUrl)
            }

            override fun onFailure(ack: String) {
                Log.d("storage", "failed " + ack)
            }
        })
    }

}