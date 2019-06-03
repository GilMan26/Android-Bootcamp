package com.example.memories.AfterLogin.AlbumTab


import android.app.Activity
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.memories.R
import com.example.memories.Repository.Album
import com.example.memories.databinding.FragmentAddAlbumBinding


class AddAlbumFragment : Fragment(), IAddAlbum.IAddAlbumView {

    lateinit var binding: FragmentAddAlbumBinding
    lateinit var presenter: AddAlbumPresenter
    private var profileImage: Bitmap? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_album, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = AddAlbumPresenter(this)
        binding.imageView.setOnClickListener {
            imageExtractor()
        }
        binding.button.setOnClickListener {

        }
    }

    override fun requestAlbum(album: Album) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun imageExtractor() {
//        val browseImageIntent = Intent(
//                Intent.ACTION_PICK,
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//        )
//        startActivityForResult(browseImageIntent, 505)
        var intent = Intent()
        intent.setAction(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent, 1011)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1011) {
            if (data != null) {
                profileImage = data.extras?.getParcelable("data")!!
                binding.imageView.setImageBitmap(profileImage)
//                    val imageIntent=Intent(ACTION_GET_CONTENT)
//                    imageIntent.putExtra("data", data)
//                    imageIntent.setDataAndType(data.data, "image/*")
//                    val cropImageIntent = Intent("com.android.camera.action.CROP")
//                    cropImageIntent.setDataAndType(data.data, "image/*")
//                    cropImageIntent.putExtra("crop", "true")
//                    cropImageIntent.putExtra("scale", "true")
//                    cropImageIntent.putExtra("aspectX", 1)
//                    cropImageIntent.putExtra("aspectY", 1)
//                    cropImageIntent.putExtra("outputX", 150)
//                    cropImageIntent.putExtra("outputY", 150)
//                    cropImageIntent.putExtra("return-data", true)
//                    startActivityForResult(imageIntent, 606)
            }
//            } else if (requestCode == 606) {
//                if (data != null) {
//                    profileImage = data.extras?.getParcelable("data")!!
//                    binding.imageView.setImageBitmap(profileImage)
//                }
//            }
        }

    }
}
