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

class ImageListFragment : BaseFragment(), IImageList.IImageListView {

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
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_image_list, container, false)
//        Log.d("data", savedInstanceState.toString())
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
        adapter = ImageAdapter(photos)
        presenter.getImages(albumRef)
        binding.imageRecycler.layoutManager = StaggeredGridLayoutManager(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS, StaggeredGridLayoutManager.VERTICAL)
        binding.addImageFab.setOnClickListener {
            fragmentTransactionHandler.pushFragment(AddImageFragment.getInstance(albumRef))
        }

    }

    override fun populateList(photos: ArrayList<Photo>) {
        this.photos = photos
        adapter.addImages(photos)
    }


}
