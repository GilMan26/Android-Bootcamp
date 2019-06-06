package com.example.memories.Repository

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayOutputStream
import com.google.android.gms.tasks.Continuation as Continuation1
import com.google.android.gms.tasks.Continuation as Continuation2

object DataManager {

    lateinit var database: FirebaseDatabase
    lateinit var storage: FirebaseStorage

    fun createAlbum(album: Album, iAlbumCreateListener: IAlbumCreateListener) {
        val albumRef = database.getReference("/users/"+LoginHelper.firebaseUser.uid+"/albums")
        var key = albumRef.push().key
        if (key != null) {
            album.id = key
            albumRef.child(key).setValue(album).addOnSuccessListener {
                iAlbumCreateListener.onCreateSuccess("Album Created Successfully")
            }
                    .addOnFailureListener{
                        iAlbumCreateListener.onCreateFailure("Album Creation Failed")
                    }
        }
    }


    fun updateTimeline(image:Photo, iImelineUpdateListener: ITImelineUpdateListener){
        val timelineRef= database.getReference("users/"+LoginHelper.firebaseUser.uid+"/timeline")
        val key=timelineRef.push().key
        if(key!=null){
            image.id=key
            timelineRef.child(key).setValue(image).addOnSuccessListener {
                iImelineUpdateListener.onUpdateSuccess("success")
            }
                    .addOnFailureListener{
                        iImelineUpdateListener.onUpdateFailure("failure")
                    }
        }
    }

    fun uploadImage(title:String, image:Bitmap, iImageUploadCallback: IImageUploadCallback){
        val storageReference= storage.reference
        val imageReference=storageReference.child("images/"+title)
        var downloadUri =""
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = imageReference.putBytes(data)
        val urlTask = uploadTask.continueWithTask(Continuation2<UploadTask.TaskSnapshot, Task<Uri>> Continuation1@{ task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    Log.d("storage", "failed")
                    throw it
                }
            }
            return@Continuation1 imageReference.downloadUrl
        }).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                downloadUri = task.result.toString()
                iImageUploadCallback.onSuccess(downloadUri)
                Log.d("storage", downloadUri)
            } else {
                // Handle failures
                // ...
            }
        }
    }


    fun loadAlbums(iLoadAlbumCallback: ILoadAlbumCallback) {
        val albumRef = database.getReference("users/"+LoginHelper.firebaseUser.uid+"/albums")
        var albums = ArrayList<Album>()
        albumRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.d("tag", "cancelled")
                iLoadAlbumCallback.onFailure("failed")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (albumSnapshot in dataSnapshot.getChildren()) {
                    Log.d("tag", dataSnapshot.toString())
                    var album = albumSnapshot.getValue(Album::class.java)
                    if (album != null){
                        albums.add(album)
                        Log.d("tag","size : "+ albums.size.toString())
                    }

                }
                iLoadAlbumCallback.onSuccess(albums)
            }
        })
    }





    fun loadTimeline(){
        val imageRef= database.getReference("users/"+LoginHelper.firebaseUser.uid+"/timeline")
        var timeline=ArrayList<Photo>()
        imageRef.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


        })
    }


    interface IAlbumCreateListener{

        fun onCreateSuccess(ack:String)

        fun onCreateFailure(ack:String)

    }

    interface ITImelineUpdateListener{

        fun onUpdateSuccess(ack:String)

        fun onUpdateFailure(ack:String)
    }

    interface ILoadAlbumCallback {

        fun onSuccess(albums:ArrayList<Album>)

        fun onFailure(ack:String)

    }

    interface IImageUploadCallback{

        fun onSuccess(downloadUrl:String)

        fun onFailure(ack:String)
    }
}