package com.example.memories.login


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.memories.BaseFragment
import com.example.memories.afterlogin.MainActivity
import com.example.memories.databinding.FragmentSignUpBinding
import com.example.memories.repository.User
import com.google.firebase.auth.FirebaseUser


class SignUpFragment : BaseFragment(), ISignupContract.ISignUpView {

    lateinit var bitmap: Bitmap
    lateinit var binding: FragmentSignUpBinding
    lateinit var signUpPresenter: SignUpPresenter
    var user = User()

    companion object {

        fun getInstance(): SignUpFragment {
            var fragment = SignUpFragment()
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
        binding.btnSignUp.setOnClickListener {
            signUpPresenter.requestSignup(binding.userSignET.text.toString(), binding.passSignET.text.toString(), binding.userNameET.text.toString(), bitmap)
        }

        binding.userFormIV.setOnClickListener {
            imageExtractor()
        }


    }

    override fun showProgress() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    override fun showValidationError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun showSignupError(error: String) {
        Log.d("signup", error)
    }

    override fun loginSuccessful(firebaseUser: FirebaseUser) {
        var intent = Intent(context, MainActivity::class.java)
        intent.putExtra("user", firebaseUser)
        Log.d("user", user.toString())
        startActivity(intent)
        Toast.makeText(context, "SignUp Successful", Toast.LENGTH_LONG).show()
    }

    override fun hideProgress() {
        binding.progressCircular.visibility = View.GONE
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


