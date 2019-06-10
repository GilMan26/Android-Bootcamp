package com.example.memories.afterlogin.timeline

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.memories.BaseFragment
import com.example.memories.R
import com.example.memories.afterlogin.album.ImageFragment
import com.example.memories.databinding.FragmentTimelineBinding
import com.example.memories.repository.Photo

class TimelineFragment : BaseFragment(), ITimelineContract.ITimelineView, TImelineAdapter.TimelineClickHandler {

    lateinit var binding: FragmentTimelineBinding
    lateinit var adapter: TImelineAdapter
    lateinit var presenter: TimelinePresenter
    var list = ArrayList<Photo>()

    companion object {
        private var BUNDLE_ARG = "key"

        fun getInstance(data: String): TimelineFragment {
            var fragment = TimelineFragment()
            var bundle = Bundle()
            bundle.putString(BUNDLE_ARG, data)
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_timeline, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = TimelinePresenter(this)
        presenter.loadImages()
        adapter = TImelineAdapter(list, this)
        binding.timelineRV.adapter = adapter
        binding.timelineRV.layoutManager = LinearLayoutManager(context)

    }

    override fun imageSelect() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        binding.timelineProgress.visibility = View.GONE
    }

    override fun showProgress() {
        binding.timelineProgress.visibility = View.VISIBLE
    }

    override fun populateList(list: ArrayList<Photo>) {
        adapter.addList(list)
    }

    override fun imageClick(url: String) {
        fragmentTransactionHandler.pushFragment(ImageFragment.getInstance(url))
    }
}