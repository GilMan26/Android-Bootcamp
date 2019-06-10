package com.example.memories.afterlogin.timeline

import com.example.memories.IBaseView
import com.example.memories.repository.Photo

interface ITimelineContract {
    interface ITimelineView : IBaseView {

        fun imageSelect()

        fun populateList(list : ArrayList<Photo>)

    }

    interface ITimelinePresenter{

        fun loadImages()

        fun getImage()



    }
}