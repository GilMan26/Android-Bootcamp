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
import com.example.memories.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException


class LoginFragment : Fragment(), ILoginContract.ILoginView {

    lateinit var loginPresenter: LoginPresenter
    lateinit var binding: FragmentLoginBinding
    val REQUEST_CODE_GOOGLE=101

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

       binding.googleSignButton.setOnClickListener {
           googleLogin()
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
                loginPresenter.requstGoogleLogin(account)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("signin", "Google sign in failed", e)
            }
        }
    }
}
