package com.example.memories.afterlogin.album

import android.graphics.Bitmap
import android.util.Log
import com.example.memories.repository.DataManager
import com.example.memories.repository.Photo

class AddImagePresenter(val iAddImageView: IAddImage.IAddImageView) : IAddImage.IAddImagePresenter {

    override fun uploadImage(title: String, bitmap: Bitmap, ref: String) {
        DataManager.uploadImage(title, bitmap, object : DataManager.IImageUploadCallback {
            override fun onSuccess(downloadUrl: String) {
                val tsLong = System.currentTimeMillis() / 1000
                val ts = tsLong.toString()
                var photo = Photo("", title, downloadUrl, ts, "")
                DataManager.addImage(photo, ref, object : DataManager.IAddImageCallBack {
                    override fun onSuccess(downloadUrl: String) {
                        Log.d("upload", "success")
                        iAddImageView.uploadSuccess()
                    }

                    override fun onFailure(ack: String) {
                        Log.d("upload", "failure")
                    }
                })
                DataManager.updateTimeline(photo, object : DataManager.ITimelineUpdateListener{
                    override fun onUpdateFailure(ack: String) {
                        Log.d("timeline", "timeline update successful")
                    }

                    override fun onUpdateSuccess(ack: String) {
                        Log.d("timeline", "timeline update unsuccessful")
                    }
                })
                Log.d("storage", downloadUrl)
            }

            override fun onFailure(ack: String) {
                Log.d("storage", "failed : " + ack)
            }
        })
    }

}