package com.example.memories.AfterLogin.AlbumTab

import com.example.memories.Repository.Album
import com.example.memories.Repository.DataManager

class AlbumListPresenter(val iAlbumListView: IAlbumList.IAlbumListView) : IAlbumList.IAlbumListPresenter {


    override fun addAlbum(album:Album) {
        DataManager.createAlbum(album)
    }


    override fun getAlbums( ) {
        var albums=DataManager.loadAlbums()
        iAlbumListView.loadAlbums(albums)
    }

    override fun getImages() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}