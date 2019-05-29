package com.example.memories.AfterLogin

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