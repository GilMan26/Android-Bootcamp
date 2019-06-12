package com.example.memories.afterlogin


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.memories.R
import com.example.memories.afterlogin.album.AlbumListFragment


class ListEmptyFragment : Fragment() {


    companion object {

        private var BUNDLE_ARG = "key"

        fun getInstance(): ListEmptyFragment {
            var fragment = ListEmptyFragment()
//            var bundle=Bundle()
//            bundle.putString(BUNDLE_ARG, data)
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_empty, container, false)
    }


}
