package com.example.memories.afterlogin.timeline

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.memories.R

class TimelineFragment:Fragment(),ITimelineContract.ITimelineView{

    companion object{
        private var BUNDLE_ARG="key"

        fun getInstance(data:String): TimelineFragment {
            var fragment= TimelineFragment()
            var bundle=Bundle()
            bundle.putString(BUNDLE_ARG, data)
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_timeline, container, false)
    }

    override fun imageSelect() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}