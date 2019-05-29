package com.example.memories.AfterLogin

import com.example.memories.Repository.Album
import com.example.memories.Repository.DataManager

class CategoryListPresenter(val iCategoryListView: ICategoryListContract.ICategoryListView) :ICategoryListContract.ICategoryListPresenter{


    override fun addAlbum(album:Album) {
        DataManager.createAlbum(album)
    }


    override fun getAlbums() {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getImages() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}