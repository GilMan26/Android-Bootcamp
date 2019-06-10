package com.example.memories.login

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
import com.example.memories.BaseFragment
import com.example.memories.R
import com.example.memories.databinding.FragmentUserFormBinding
import com.example.memories.repository.User

class UserFormFragment : BaseFragment() , IUserFormContract.IUserFormView{

    lateinit var binding: FragmentUserFormBinding
    lateinit var bitmap: Bitmap
    lateinit var presenter:UserFormPresenter
    lateinit var uid:String

    companion object {

        fun getInstance(id:String): UserFormFragment {
            var fragment = UserFormFragment()
            var bundle=Bundle()
            bundle.putString("uid" ,id)
            return fragment
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_form, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        uid= arguments?.get("uid") as String
        presenter= UserFormPresenter(this)
        binding.userFormIV.setOnClickListener{
            imageExtractor()
        }

        binding.uploadBtn.setOnClickListener{
            presenter.saveUser(binding.userNameET.text.toString(), bitmap)
        }
    }

    override fun getUser(name: String, bitmap: Bitmap) {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
                binding.userFormIV.setImageURI(uri)
                bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, uri)

            } else {

            }
        }
    }
}