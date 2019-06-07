package com.example.memories.afterlogin.album

import com.example.memories.repository.Photo

interface IImageList {

    interface IImageListView {

        fun populateList(photos: ArrayList<Photo>)

    }


    interface IImageListPresenter {

        fun getImages(ref: String)

    }

}