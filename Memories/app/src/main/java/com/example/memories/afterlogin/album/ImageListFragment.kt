package com.example.memories.afterlogin.album

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.memories.BaseFragment
import com.example.memories.R
import com.example.memories.repository.Photo
import com.example.memories.databinding.FragmentImageListBinding

class ImageListFragment : BaseFragment(), IImageList.IImageListView, ImageAdapter.ImageClick {

    lateinit var binding: FragmentImageListBinding
    lateinit var albumRef: String
    lateinit var presenter: ImageListPresenter
    var photos = ArrayList<Photo>()
    lateinit var adapter: ImageAdapter

    companion object {

        private var ALBUM_REF = "ref"
        fun getInstance(data: String): ImageListFragment {
            var fragment = ImageListFragment()
            var bundle = Bundle()
            bundle.putString(ALBUM_REF, data)
            Log.d("instance test", data)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_image_list, container, false)
        if (arguments != null) {
            albumRef = arguments!!.getString(ALBUM_REF)
            Log.d("on create test", albumRef)
        }
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("data", savedInstanceState.toString())
        presenter = ImageListPresenter(this)
        adapter = ImageAdapter(photos, this)
        presenter.getImages(albumRef)
        binding.imageRecycler.adapter=adapter
        binding.imageRecycler.layoutManager = GridLayoutManager(context, 2)
        binding.addImageFab.setOnClickListener {
            fragmentTransactionHandler.pushFragment(AddImageFragment.getInstance(albumRef))
        }

        binding.refreshImages.setOnRefreshListener {
            presenter.getImages(albumRef)
        }

    }

    override fun populateList(photos: ArrayList<Photo>) {
        this.photos = photos
        adapter.addImages(photos)
        binding.refreshImages.setRefreshing(false)
    }

    override fun onClick(url: String) {
        fragmentTransactionHandler.pushFragment(ImageFragment.getInstance(url))
    }


}
