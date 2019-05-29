package com.example.memories.AfterLogin

import com.example.memories.IBaseView
import com.example.memories.Repository.Album

interface ICategoryListContract {

    interface ICategoryListView: IBaseView {
        fun addRequest(album: Album)

        fun categorySelect()

    }

    interface ICategoryListPresenter{

        fun getAlbums()

        fun getImages()

        fun addAlbum(album: Album)

    }
}