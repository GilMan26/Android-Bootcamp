package com.example.memories.AfterLogin.AlbumTab

import com.example.memories.IBaseView
import com.example.memories.Repository.Album

interface IAlbumList {

    interface IAlbumListView : IBaseView {
        fun addRequest(album: Album)

        fun categorySelect()

        fun loadAlbums(albums: ArrayList<Album>)

    }

    interface IAlbumListPresenter {

        fun getAlbums()

        fun getImages()


    }
}