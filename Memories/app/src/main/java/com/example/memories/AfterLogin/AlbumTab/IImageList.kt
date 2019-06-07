package com.example.memories.AfterLogin.AlbumTab

import com.example.memories.Repository.Photo

interface IImageList {

    interface IImageListView{

        fun populateList(photos: ArrayList<Photo>)

    }


    interface IImageListPresenter{

        fun getImages(ref:String)

    }

}