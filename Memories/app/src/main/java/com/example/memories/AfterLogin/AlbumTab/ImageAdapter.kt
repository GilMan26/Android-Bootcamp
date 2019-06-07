package com.example.memories.AfterLogin.AlbumTab

import android.provider.ContactsContract
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.memories.R
import com.example.memories.Repository.Album
import com.example.memories.Repository.Photo

class ImageAdapter(val images:ArrayList<Photo>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.image_row_layout, parent, false)
        return ImageViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun addImages(photos: List<Photo>){
        images.addAll(photos)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(imageViewHolder: ImageViewHolder, position: Int) {
       imageViewHolder.bindImage(images[position])
    }


    class ImageViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        fun bindImage(photo:Photo){
            val imageView=itemView.findViewById(R.id.image) as ImageView
            val context=imageView.context
            if(context!=null){
                Glide.with(context!!)
                        .load(photo.url)
                        .into(imageView)
            }
        }
    }
}