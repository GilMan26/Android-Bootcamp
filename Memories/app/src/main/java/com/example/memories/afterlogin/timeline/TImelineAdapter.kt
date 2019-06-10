package com.example.memories.afterlogin.timeline

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.memories.R
import com.example.memories.repository.Photo
import kotlinx.android.synthetic.main.image_row_layout.view.*
import kotlinx.android.synthetic.main.timeline_row.view.*

class TImelineAdapter(var list: List<Photo>, val clickHandler: TimelineClickHandler) : RecyclerView.Adapter<TImelineAdapter.TimeLineViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TimeLineViewHolder {
        val itemView=LayoutInflater.from(viewGroup.context).inflate(R.layout.timeline_row,viewGroup, false )
        return TimeLineViewHolder(itemView)
    }

    fun addList(images:ArrayList<Photo>){
        this.list=images
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(timelineViewHolder: TimeLineViewHolder, position: Int) {
        timelineViewHolder.bindImage(list[position])
        timelineViewHolder.itemView.setOnClickListener{
            clickHandler.imageClick(list[position].url)
        }
    }


    class TimeLineViewHolder(view:View):RecyclerView.ViewHolder(view){
        fun bindImage(photo: Photo) {
            itemView.nameTV.text=photo.title
            itemView.timeTV.text=photo.time
            val context = itemView.timelineIV.context
            if (context != null) {
                Glide.with(context!!)
                        .load(photo.url)
                        .into(itemView.timelineIV)
            }
            itemView.setOnClickListener{

            }
        }
    }

    interface TimelineClickHandler{

        fun imageClick(url:String)

    }
}