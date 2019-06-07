package com.example.memories.AfterLogin.AlbumTab

import android.util.Log
import com.example.memories.Repository.DataManager
import com.example.memories.Repository.Photo

class ImageListPresenter(var iImageListView: IImageList.IImageListView) : IImageList.IImageListPresenter {

    override fun getImages(ref: String) {
        DataManager.loadImages(ref, object : DataManager.ILoadImageCallback {
            override fun onSuccess(images: ArrayList<Photo>) {
                Log.d("getImage", images.size.toString())
                iImageListView.populateList(images)
            }

            override fun onFailure(ack: String) {
                Log.d("getImage", ack)
            }
        })
    }

}