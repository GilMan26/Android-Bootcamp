package com.example.memories.afterlogin.album


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.memories.BaseFragment

import com.example.memories.R
import com.example.memories.databinding.FragmentImageBinding
import java.net.URL


class ImageFragment : BaseFragment() {

    lateinit var binding:FragmentImageBinding
    lateinit var url:String

    companion object{
        val URL_REF="url"

        fun getInstance(url:String): ImageFragment{
            var fragment = ImageFragment()
            var bundle = Bundle()
            bundle.putString(URL_REF, url)

            fragment.arguments = bundle

            return fragment
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_image, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        if(arguments!=null)
            url= arguments!!.getString("url")
        super.onActivityCreated(savedInstanceState)
        Glide.with(context!!)
                .load(url)
                .into(binding.imageView)
    }


}
