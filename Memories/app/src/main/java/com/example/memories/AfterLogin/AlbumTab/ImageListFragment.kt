package com.example.memories.AfterLogin.AlbumTab

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.memories.R
import com.example.memories.databinding.FragmentImageListBinding

class ImageListFragment : Fragment() {

    lateinit var binding:FragmentImageListBinding
    lateinit var bitmap: Bitmap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_image_list, container,  false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.addImageFab.setOnClickListener{
//            imageExtractor()
        }

    }



}
