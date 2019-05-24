package com.example.memories


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.memories.databinding.FragmentLoginBinding
import com.example.memories.databinding.FragmentSignUpBinding


class LoginFragment : Fragment(), ILoginContract.ILoginView {

    lateinit var loginPresenter: LoginPresenter
    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loginPresenter = LoginPresenter(this)
        binding.btnLogin.setOnClickListener {
            loginPresenter.requestLogin(binding.userLoginET.text.toString(), binding.passLoginET.text.toString())
        }
    }

    override fun showProgress() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressCircular.visibility = View.GONE
    }

    override fun showValidationError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoginError(string: String) {
        Toast.makeText(context, string, Toast.LENGTH_LONG).show()
    }

    override fun loginSuccessful() {
        Toast.makeText(context, "Login Successful", Toast.LENGTH_LONG).show()
    }


}
