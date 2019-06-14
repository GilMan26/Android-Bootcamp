package com.example.memories.afterlogin.album

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
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

    override fun onResume() {
        super.onResume()
        presenter.getImages(albumRef)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("data", savedInstanceState.toString())
        binding.imageListToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        binding.imageListToolbar.setNavigationOnClickListener{
            fragmentManager?.popBackStackImmediate()
        }
        presenter = ImageListPresenter(this)
        adapter = ImageAdapter(photos, this)
        presenter.getImages(albumRef)
        binding.imageRecycler.adapter=adapter
        binding.imageRecycler.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.addImageFab.setOnClickListener {
            fragmentTransactionHandler.pushFullFragment(AddImageFragment.getInstance(albumRef))
        }

        binding.refreshImages.setOnRefreshListener {
            presenter.getImages(albumRef)
        }

    }

    override fun populateList(photos: ArrayList<Photo>) {
        this.photos = photos
        adapter.addImages(photos)
        if(adapter.itemCount==0)
            binding.imageListTV.visibility=View.VISIBLE
        binding.refreshImages.setRefreshing(false)
    }

    override fun showProgress() {
        binding.imageListProgress.visibility=View.VISIBLE
    }

    override fun hideProgress() {
        binding.imageListProgress.visibility=View.GONE
    }

    override fun onClick(url: String) {
        fragmentTransactionHandler.pushFullFragment(ImageFragment.getInstance(url))
    }


}
