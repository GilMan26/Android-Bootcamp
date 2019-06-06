package com.example.memories.AfterLogin.AlbumTab

import android.provider.ContactsContract
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.memories.Repository.Photo

class ImageAdapter(val images:ArrayList<Photo>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ImageViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(p0: ImageViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class ImageViewHolder(view:View): RecyclerView.ViewHolder(view) {

    }
}