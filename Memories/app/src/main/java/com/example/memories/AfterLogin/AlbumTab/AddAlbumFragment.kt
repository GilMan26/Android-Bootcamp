package com.example.memories.AfterLogin.AlbumTab


import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.memories.AfterLogin.IAlbumSuccess
import com.example.memories.Repository.Album
import com.example.memories.databinding.FragmentAddAlbumBinding


class AddAlbumFragment : Fragment(), IAddAlbum.IAddAlbumView {

    lateinit var binding: FragmentAddAlbumBinding
    lateinit var presenter: AddAlbumPresenter
    lateinit var iAlbumSuccess: IAlbumSuccess
    lateinit var bitmap:Bitmap

    companion object{
        private var BUNDLE_ARG="key"

        fun getInstance(data:String): AddAlbumFragment{
            var fragment=AddAlbumFragment()
            var bundle=Bundle()
            bundle.putString(BUNDLE_ARG, data)
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, com.example.memories.R.layout.fragment_add_album, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = AddAlbumPresenter(this)
        binding.button.setOnClickListener {
            presenter.validateAlbum(binding.albumName.text.toString(), binding.albumMessage.text.toString(), bitmap)
        }
        binding.imageView.setOnClickListener {
//            imageExtractor()
        }
    }

    fun setInstance(iAlbumSuccess: IAlbumSuccess) {
        this.iAlbumSuccess = iAlbumSuccess
    }

    override fun requestAlbum(album: Album) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showValidatiton(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun createResponse(ack: String) {
        Toast.makeText(context, ack, Toast.LENGTH_LONG).show()
        createSuccess()
    }

    override fun createSuccess() {
        iAlbumSuccess.switchFragment()
    }




}
