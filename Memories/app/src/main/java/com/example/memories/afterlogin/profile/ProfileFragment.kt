package com.example.memories.afterlogin.profile

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.memories.BaseFragment
import com.example.memories.R
import com.example.memories.databinding.FragmentProfileBinding
import com.example.memories.login.SignUpFragment

class ProfileFragment: BaseFragment(), IProfileContract.IProfileView{
    lateinit var binding:FragmentProfileBinding
    lateinit var presenter: ProfilePresenter

    companion object{
        private var BUNDLE_ARG="key"

        fun getInstance(data:String): ProfileFragment {
            var fragment= ProfileFragment()
            var bundle=Bundle()
            bundle.putString(BUNDLE_ARG, data)
            return fragment
        }
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter= ProfilePresenter(this)
        presenter.getDetials()
        binding.logoutbtn.setOnClickListener{
            presenter.logout()
        }
    }

    override fun requestChangeProfile() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        binding.profileProgress.visibility=View.GONE
    }

    override fun showProgress() {
        binding.profileProgress.visibility=View.VISIBLE
    }

    override fun inflateData(name: String, url: String) {
        binding.profileName.text=name
        if(context!=null) {
            Glide.with(context!!)
                    .load(url)
                    .into(binding.profileImage)
        }
    }

    override fun logout() {
        System.exit(0)
    }
}