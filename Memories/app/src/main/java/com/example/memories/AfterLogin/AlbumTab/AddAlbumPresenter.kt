package com.example.memories.AfterLogin.AlbumTab

import android.graphics.Bitmap
import com.example.memories.Repository.Album
import com.example.memories.Repository.DataManager
import com.example.memories.Repository.Photo

class AddAlbumPresenter(val iAddAlbumView: IAddAlbum.IAddAlbumView): IAddAlbum.IAddAlbumPresenter {



    override fun validateAlbum(title: String, message: String) {
        if(title.isEmpty())
            iAddAlbumView.showValidatiton("Album Title mandatory")
        else{
            val tsLong = System.currentTimeMillis() / 1000
            val ts = tsLong.toString()
            val photos=ArrayList<Photo>()
            if(message==null){
                var album=Album("", title,ts, "",  photos)
                DataManager.createAlbum(album, object : DataManager.IAlbumCreateListener{
                    override fun onCreateSuccess(ack: String) {
                        iAddAlbumView.createResponse(ack)
//                        iAddAlbumView.createSuccess()
                    }

                    override fun onCreateFailure(ack: String) {
                        iAddAlbumView.createResponse(ack)
                    }

                })
            }
            else{
                var album=Album("", title,ts, message,  photos)
                DataManager.createAlbum(album, object : DataManager.IAlbumCreateListener{
                    override fun onCreateSuccess(ack: String) {
                        iAddAlbumView.createResponse(ack)
//                        iAddAlbumView.createSuccess()
                    }

                    override fun onCreateFailure(ack: String) {
                        iAddAlbumView.createResponse(ack)
                    }

                })
            }


        }
    }


}