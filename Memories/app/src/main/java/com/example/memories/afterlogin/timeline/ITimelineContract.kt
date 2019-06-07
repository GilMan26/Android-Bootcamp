package com.example.memories.afterlogin.timeline

import com.example.memories.IBaseView

interface ITimelineContract {
    interface ITimelineView : IBaseView {

        fun imageSelect()

    }

    interface ITimelinePresenter{

        fun loadImages()

        fun getImage()



    }
}