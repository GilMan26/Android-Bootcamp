package com.example.memories.Repository

import android.util.Log
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage

object DataManager {

    lateinit var database: FirebaseDatabase
    lateinit var storage: FirebaseStorage

    fun createAlbum(album: Album) {
        val albumRef = database.getReference("/albums")
        var key = albumRef.push().key
        if (key != null) {
            album.id = key
            albumRef.child(key).setValue(album)
        }

    }

    fun loadAlbums(): ArrayList<Album> {
        val dbRef = database.reference
        var albums = ArrayList<Album>()
        var albumQuery: Query
        albumQuery = dbRef.orderByChild("uid").equalTo(LoginHelper.firebaseUser?.uid)
        albumQuery.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.d("tag", "cancelled")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                albums.clear()
                for (postSnapshot in dataSnapshot.getChildren()) {
                    var album = postSnapshot.getValue(Album::class.java)
                    if (album != null)
                        albums.add(album)
                }
            }
        })
        return albums
    }

//    fun uploadImage() {
//
//    }
//
//    fun loadTimeline(){
//        val imageRef= database.getReference("/photos")
//        imageRef.addValueEventListener(object : ValueEventListener{
//
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//
//        })
//    }
}