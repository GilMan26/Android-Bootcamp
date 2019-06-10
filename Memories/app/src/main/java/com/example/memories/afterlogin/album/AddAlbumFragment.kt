package com.example.memories.afterlogin.album


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.memories.BaseFragment
import com.example.memories.repository.Album
import com.example.memories.databinding.FragmentAddAlbumBinding


class AddAlbumFragment : BaseFragment(), IAddAlbum.IAddAlbumView {

    lateinit var binding: FragmentAddAlbumBinding
    lateinit var presenter: AddAlbumPresenter

    companion object {
        private var BUNDLE_ARG = "key"

        fun getInstance(): AddAlbumFragment {
            var fragment = AddAlbumFragment()
//            var bundle=Bundle()
//            bundle.putString(BUNDLE_ARG, d)
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
            presenter.validateAlbum(binding.albumName.text.toString(), binding.albumMessage.text.toString())
        }
//        binding.imageView.setOnClickListener {
//                        imageExtractor()
//        }
    }


    override fun requestAlbum(album: Album) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showValidatiton(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun createResponse(ack: String) {
        Toast.makeText(context, ack, Toast.LENGTH_LONG).show()
        fragmentTransactionHandler.pushFragment(AlbumListFragment.getInstance())
    }


}
