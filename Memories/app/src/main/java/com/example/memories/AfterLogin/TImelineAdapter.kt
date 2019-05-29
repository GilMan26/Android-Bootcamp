package com.example.memories.AfterLogin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.memories.Firebase.UPhoto

class TImelineAdapter(val list: List<UPhoto>, val context:Context) : RecyclerView.Adapter<TImelineAdapter.TimeLineViewHolder>() {



    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TimeLineViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: TimeLineViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class TimeLineViewHolder(view:View):RecyclerView.ViewHolder(view){

    }
}