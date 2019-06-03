package com.example.memories.AfterLogin.AlbumTab

import com.example.memories.Repository.Album

interface IAddAlbum {


    interface IAddAlbumView{

        fun requestAlbum(album:Album)

    }


    interface IAddAlbumPresenter{
        fun createAlbum(album:Album)
    }
}