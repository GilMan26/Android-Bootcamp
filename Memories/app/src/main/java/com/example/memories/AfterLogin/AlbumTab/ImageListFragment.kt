package com.example.memories.AfterLogin.AlbumTab

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.memories.AfterLogin.IActivityInteractor

import com.example.memories.R
import com.example.memories.Repository.Photo
import com.example.memories.databinding.FragmentImageListBinding

class ImageListFragment : Fragment(), IImageList.IImageListView {

    lateinit var binding: FragmentImageListBinding
    lateinit var albumRef: String
    lateinit var presenter: ImageListPresenter
    var photos = ArrayList<Photo>()
    lateinit var adapter: ImageAdapter
    lateinit var activityInteractor: IActivityInteractor

    companion object {

        private var BUNDLE_ARG="key"
        fun getInstance(data: String): ImageListFragment {
            var fragment = ImageListFragment()
            var bundle = Bundle()
            bundle.putString(BUNDLE_ARG, data)
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_image_list, container, false)
        if (arguments != null) {
            albumRef = arguments!!.getString("ref")
        }
        return binding.root
    }

    fun setInstance(iActivityInteractor: IActivityInteractor) {
        activityInteractor = iActivityInteractor
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = ImageListPresenter(this)
        adapter = ImageAdapter(photos)
        presenter.getImages(albumRef)
        binding.imageRecycler.layoutManager = StaggeredGridLayoutManager(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS, StaggeredGridLayoutManager.VERTICAL)
        binding.addImageFab.setOnClickListener {
            activityInteractor.addImage()
        }

    }

    override fun populateList(photos: ArrayList<Photo>) {
        this.photos = photos
        adapter.addImages(photos)
    }


}
