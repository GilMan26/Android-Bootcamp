package com.example.memories.afterlogin.album

import com.example.memories.repository.Album

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