package com.example.memories.AfterLogin.AlbumTab

import com.example.memories.Repository.Album

interface IAddAlbum {


    interface IAddAlbumView {

        fun requestAlbum(album: Album)

        fun showValidatiton(message: String)

        fun createResponse(ack: String)

    }


    interface IAddAlbumPresenter {

        fun validateAlbum(title: String, message: String)

    }
}