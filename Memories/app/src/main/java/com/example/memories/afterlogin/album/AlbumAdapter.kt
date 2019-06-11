package com.example.memories.afterlogin.album

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.memories.R
import com.example.memories.repository.Album

class AlbumAdapter(var albums: ArrayList<Album>) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {


    lateinit var clickHandler: IAlbumClickHandler
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.album_row_layout, parent, false)
        return AlbumViewHolder(itemView)
    }

    fun setInstance(iAlbumClickHandler: IAlbumClickHandler) {
        clickHandler = iAlbumClickHandler
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    fun addList(albums: ArrayList<Album>) {
        this.albums.clear()
        this.albums = albums
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(viewHolder: AlbumViewHolder, positon: Int) {
        var data = albums[positon]
        viewHolder.bindItems(data)
        viewHolder.itemView.setOnClickListener {
            Log.d("adapter test", data.id)
            clickHandler.onAlbumClick(data.id)
        }
    }


    class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(album: Album) {
            val textViewTitle = itemView.findViewById(R.id.titleTV) as TextView
            val textViewTime = itemView.findViewById(R.id.timeTV) as TextView
            textViewTitle.text = album.title
            textViewTime.text = album.time
        }
    }

    interface IAlbumClickHandler {

        fun onAlbumClick(id: String)

    }
}