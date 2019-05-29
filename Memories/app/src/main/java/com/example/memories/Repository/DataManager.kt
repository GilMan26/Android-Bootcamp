package com.example.memories.Repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

object DataManager{

    lateinit var database: FirebaseDatabase
    lateinit var storage:FirebaseStorage

    fun createAlbum(album: Album){
        val albumRef= database.getReference("/albums")
        albumRef.child(album.aId.toString()).setValue(album)
    }

    fun loadAlbums(){
        val dbRef= database.reference
        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onCancelled(databaseError: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    fun uploadImage(){

    }
}