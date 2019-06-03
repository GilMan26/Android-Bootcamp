package com.example.memories.AfterLogin.AlbumTab

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.memories.AfterLogin.IActivityInteractor
import com.example.memories.Repository.Album
import com.example.memories.databinding.FragmentAlbumsBinding




class AlbumListFragment:Fragment(), IAlbumList.IAlbumListView {

    lateinit var binding: FragmentAlbumsBinding
    lateinit var presenter: AlbumListPresenter
//    lateinit var adapter: AlbumAdapter
    lateinit var activityInteractor:IActivityInteractor
    lateinit var albums:ArrayList<Album>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=DataBindingUtil.inflate(inflater, com.example.memories.R.layout.fragment_albums, container, false)
        return binding.root
    }

    fun setInstance(iActivityInteractor: IActivityInteractor){
        this.activityInteractor=iActivityInteractor
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter= AlbumListPresenter(this)
        presenter.getAlbums()
        binding.addFab.setOnClickListener{
//            val tsLong = System.currentTimeMillis() / 1000
//            val ts = tsLong.toString()
//            var album=Album("", "demo", ts, "http://tineye.com/images/widgets/mona.jpg", "mona")
//            addRequest(album)
            activityInteractor.addAlbum()

        }
//        binding.categoryRecycler.adapter=adapter
//        binding.categoryRecycler.layoutManager=LinearLayoutManager(context)
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

    override fun loadAlbums(albums: ArrayList<Album>) {
        var albums=albums
        Log.d("tag", albums.toString())
    }





}