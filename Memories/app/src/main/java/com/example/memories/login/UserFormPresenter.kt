package com.example.memories.login

import android.graphics.Bitmap
import com.example.memories.repository.DataManager
import com.example.memories.repository.User

class UserFormPresenter(var iUserFormView: IUserFormContract.IUserFormView) : IUserFormContract.IUserFormPresenter{

    override fun saveUser(name:String, bitmap: Bitmap) {
        DataManager.uploadImage(name, bitmap, object : DataManager.IImageUploadCallback{
            override fun onSuccess(downloadUrl: String) {
                var user=User
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onFailure(ack: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}