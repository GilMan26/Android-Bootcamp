package com.example.memories.afterlogin.timeline

import android.util.Log
import com.example.memories.repository.DataManager
import com.example.memories.repository.Photo

class TimelinePresenter(val iTimelineView: ITimelineContract.ITimelineView) : ITimelineContract.ITimelinePresenter{

    override fun getImage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun loadImages() {
        iTimelineView.showProgress()
        DataManager.loadTimeline(object : DataManager.ITimelineCallback{
            override fun onSuccess(timeline: ArrayList<Photo>) {
                Log.d("timeline" , "success")
                iTimelineView.populateList(timeline)
            }

            override fun onFailure(ack: String) {
                Log.d("timeline", ack)
            }
        })
    }

}