package com.example.memories.login


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.memories.App
import com.example.memories.BaseFragment
import com.example.memories.databinding.FragmentSignUpBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException


class SignUpFragment : BaseFragment(), ISignupContract.ISignUpView {


    val REQUEST_CODE_GOOGLE=101
    lateinit var binding: FragmentSignUpBinding
    lateinit var signUpPresenter: SignUpPresenter

    companion object{

        fun getInstance():SignUpFragment{
            var fragment= SignUpFragment()
            return fragment
        }
    }

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
           fragmentTransactionHandler.pushFragment(LoginFragment.getInstance())
        }
        binding.googleSignButton.setOnClickListener {
            googleLogin()
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

    override fun googleLogin() {
        val app=activity?.application as App
        val intent=app.googleSignInClient.signInIntent
        startActivityForResult(intent, REQUEST_CODE_GOOGLE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_GOOGLE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                signUpPresenter.requstGoogleLogin(account)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("signin", "Google sign in failed", e)
            }
        }
    }

    override fun requestUserDetails(id:String) {
        fragmentTransactionHandler.pushFragment(UserFormFragment.getInstance())
    }


}


