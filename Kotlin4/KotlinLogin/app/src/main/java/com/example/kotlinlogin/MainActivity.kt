package com.example.kotlinlogin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var usernameET=findViewById<EditText>(R.id.usernameET)
        var passwordET=findViewById<EditText>(R.id.passwordET)
        var button=findViewById<Button>(R.id.loginBtn)




        button.setOnClickListener {
            if(!validateEmail(usernameET.text.toString())){
                    Toast.makeText(this, "Invalid Email",Toast.LENGTH_LONG).show()
                }
            else{
                Toast.makeText(this, "Details Submitted", Toast.LENGTH_LONG).show()
                usernameET.setText("")
                passwordET.setText("")
            }

        }
    }

    fun validateEmail(str:String): Boolean {
        return (!TextUtils.isEmpty(str) && Patterns.EMAIL_ADDRESS.matcher(str).matches())
    }
}
