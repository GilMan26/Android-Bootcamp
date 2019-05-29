package com.example.memories.AfterLogin

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.memories.Repository.Album
import com.example.memories.databinding.FragmentCategoryBinding




class CategoryListFragment:Fragment(), ICategoryListContract.ICategoryListView{

    lateinit var binding: FragmentCategoryBinding
    lateinit var presenter: CategoryListPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=DataBindingUtil.inflate(inflater, com.example.memories.R.layout.fragment_category, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter=CategoryListPresenter(this)
        binding.addFab.setOnClickListener{
            val tsLong = System.currentTimeMillis() / 1000
            val ts = tsLong.toString()
            var album=Album(1, "demo", ts, "http://tineye.com/images/widgets/mona.jpg", "mona")
            addRequest(album)
        }
    }

    override fun addRequest(album: Album) {
        presenter.addAlbum(album)
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun categorySelect() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }





}