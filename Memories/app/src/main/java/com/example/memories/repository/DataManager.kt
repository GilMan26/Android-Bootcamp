package com.example.memories.repository

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayOutputStream
import com.google.android.gms.tasks.Continuation

object DataManager {

    lateinit var database: FirebaseDatabase
    lateinit var storage: FirebaseStorage

    fun createAlbum(album: Album, iAlbumCreateListener: IAlbumCreateListener) {
        val albumRef = database.getReference("/users/" + LoginHelper.firebaseUser.uid + "/albums")
        var key = albumRef.push().key
        if (key != null) {
            album.id = key
            albumRef.child(key).setValue(album).addOnSuccessListener {
                iAlbumCreateListener.onCreateSuccess("Album Created Successfully")
            }
                    .addOnFailureListener {
                        iAlbumCreateListener.onCreateFailure("Album Creation Failed")
                    }
        }
    }

    fun addImage(image: Photo, albumRef: String, iAddImageCallBack: IAddImageCallBack) {
        Log.d("test", albumRef)
        val imageRef = database.getReference("/users/" + LoginHelper.firebaseUser.uid + "/albums/" + albumRef + "/photos")
        var key = imageRef.push().key
        if (key != null) {
            image.id = key
            imageRef.child(key).setValue(image).addOnSuccessListener {
                iAddImageCallBack.onSuccess("Image Added")
            }.addOnFailureListener {
                iAddImageCallBack.onFailure("failed")
            }
        }
    }


    fun updateTimeline(image: Photo, iImelineUpdateListener: ITimelineUpdateListener) {
        val timelineRef = database.getReference("/users/" + LoginHelper.firebaseUser.uid + "/timeline")
        val key = timelineRef.push().key
        if (key != null) {
            image.id = key
            timelineRef.child(key).setValue(image).addOnSuccessListener {
                iImelineUpdateListener.onUpdateSuccess("success")
            }
                    .addOnFailureListener {
                        iImelineUpdateListener.onUpdateFailure("failure")
                    }
        }
    }

    fun uploadImage(title: String, image: Bitmap, iImageUploadCallback: IImageUploadCallback) {
        val storageReference = storage.reference
        val imageReference = storageReference.child("images/" + title)
        var downloadUri = ""
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = imageReference.putBytes(data)
        val urlTask = uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> Continuation@{ task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    Log.d("storage", "failed")
                    throw it
                }
            }
            return@Continuation imageReference.downloadUrl
        }).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                downloadUri = task.result.toString()
                iImageUploadCallback.onSuccess(downloadUri)
                Log.d("storage", downloadUri)
            } else {
                iImageUploadCallback.onFailure("failed")
            }
        }
    }


    fun loadAlbums(iLoadAlbumCallback: ILoadAlbumCallback) {
        val albumRef = database.getReference("users/" + LoginHelper.firebaseUser.uid + "/albums")
        var albums = ArrayList<Album>()
        albumRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.d("tag", "cancelled")
                iLoadAlbumCallback.onFailure("failed")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (albumSnapshot in dataSnapshot.children) {
                    Log.d("tag", albumSnapshot.toString())
                    var album = albumSnapshot.getValue(Album::class.java)
                    if (album != null) {
                        albums.add(album)
                        Log.d("tag", "size : " + albums.size.toString())
                    }
                }
                iLoadAlbumCallback.onSuccess(albums)
            }
        })
    }

    fun loadImages(albumRef: String, iLoadImageCallback: ILoadImageCallback) {
        val photosRef = database.getReference("users/" + LoginHelper.firebaseUser.uid + "/albums/" + albumRef + "/photos")
        var images = ArrayList<Photo>()
        Log.d("photo", "in data manager"+photosRef.toString())
        photosRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d("photo", dataSnapshot.toString())
                for (photoSnapshot in dataSnapshot.children) {
                    Log.d("photo", photoSnapshot.toString())
                    var photo = photoSnapshot.getValue(Photo::class.java)
                    Log.d("photo", photo.toString())
                    if (photo != null) {
                        images.add(photo)
                        Log.d("photos", images.toString())
                    }
                }
                iLoadImageCallback.onSuccess(images)
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.d("photo" , p0.toString())
                iLoadImageCallback.onFailure(p0.toString())
            }
        })
    }

    fun loadTimeline(iTimelineCallback: ITimelineCallback) {
        val imageRef = database.getReference("users/" + LoginHelper.firebaseUser.uid + "/timeline")
        var timeline = ArrayList<Photo>()
        imageRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (photoSnapshot in dataSnapshot.children) {
                    var photo = photoSnapshot.getValue(Photo::class.java)
                    if (photo != null) {
                        timeline.add(photo)
                    }
                }
                iTimelineCallback.onSuccess(timeline)

            }

            override fun onCancelled(error: DatabaseError) {
                iTimelineCallback.onFailure(error.toString())
            }


        })
    }


    interface IAlbumCreateListener {

        fun onCreateSuccess(ack: String)

        fun onCreateFailure(ack: String)

    }

    interface ITimelineUpdateListener {

        fun onUpdateSuccess(ack: String)

        fun onUpdateFailure(ack: String)

    }

    interface ILoadAlbumCallback {

        fun onSuccess(albums: ArrayList<Album>)

        fun onFailure(ack: String)

    }

    interface IImageUploadCallback {

        fun onSuccess(downloadUrl: String)

        fun onFailure(ack: String)
    }

    interface ITimelineCallback {

        fun onSuccess(timeline: List<Photo>)

        fun onFailure(ack: String)
    }

    interface IAddImageCallBack {

        fun onSuccess(ack: String)

        fun onFailure(ack: String)
    }

    interface ILoadImageCallback {

        fun onSuccess(images: ArrayList<Photo>)

        fun onFailure(ack: String)
    }
}