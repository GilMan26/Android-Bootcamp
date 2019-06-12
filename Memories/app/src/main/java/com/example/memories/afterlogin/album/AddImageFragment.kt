package com.example.memories.afterlogin.album


import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.memories.BaseFragment

import com.example.memories.R
import com.example.memories.databinding.FragmentAddImageBinding


class AddImageFragment : BaseFragment(), IAddImage.IAddImageView {


    lateinit var presenter: AddImagePresenter
    lateinit var bitmap: Bitmap
    lateinit var albumRef: String
    lateinit var binding: FragmentAddImageBinding

    companion object {
        private val ALBUM_REF = "ref"

        fun getInstance(ref: String): AddImageFragment {
            var fragment = AddImageFragment()
            var bundle = Bundle()
            bundle.putString(ALBUM_REF, ref)

            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_image, container, false)
        if (arguments != null) {
            albumRef = arguments!!.getString(ALBUM_REF)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = AddImagePresenter(this)
        binding.addImage.setOnClickListener {
            presenter.uploadImage(binding.imageName.text.toString(), bitmap, albumRef)
        }

        binding.imageView.setOnClickListener {
            imageExtractor()
        }

    }

    override fun showProgress() {
        binding.addImageProgress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.addImageProgress.visibility = View.GONE
    }

    override fun uploadSuccess() {
        Log.d("success", "Success")
        fragmentTransactionHandler.pushFragment(ImageListFragment.getInstance(albumRef))
    }


    private fun imageExtractor() {
        var intent = Intent()
        intent.setAction(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent, 1011)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1011) {
            if (data != null) {
                var uri: Uri? = data.data
                binding.imageView.setImageURI(uri)
                bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, uri)

            } else {

            }
        }
    }

}
