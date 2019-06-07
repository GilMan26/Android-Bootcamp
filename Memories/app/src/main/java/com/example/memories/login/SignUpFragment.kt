package com.example.memories.login


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.memories.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment(), ISignupContract.ISignUpView {


    lateinit var binding: FragmentSignUpBinding
    lateinit var signUpPresenter: SignUpPresenter
    lateinit var iSwitchView: ISwitchView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        signUpPresenter = SignUpPresenter(this)
        binding.btnSign.setOnClickListener {
            signUpPresenter.requestSignup(binding.userSignET.text.toString(), binding.passSignET.text.toString())
        }
        binding.btnLogin.setOnClickListener {
            Log.d("click", "in fragment")
            iSwitchView.replaceFragment()
        }
    }

    override fun showProgress() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    override fun showValidationError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun showSignupError(error: String) {
    }

    override fun loginSuccessful() {
        Toast.makeText(context, "Login succesful", Toast.LENGTH_LONG).show()
    }

    override fun hideProgress() {
        binding.progressCircular.visibility = View.GONE
    }

    fun setSwitchInstance(iSwitchView: ISwitchView) {
        this.iSwitchView = iSwitchView
    }

    interface ISwitchView {
        fun replaceFragment()
    }


}


