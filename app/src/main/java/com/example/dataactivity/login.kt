package com.example.dataactivity

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity

class login : AppCompatActivity(), View.OnClickListener {
    private lateinit var username : EditText
    private lateinit var password : EditText
    private lateinit var Loginbtn : Button
    private lateinit var loginConstraint : ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding()
        Loginbtn.setOnClickListener(this)
        loginConstraint.setOnClickListener(this)
    }

    private fun binding() {
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        Loginbtn = findViewById(R.id.login)
        loginConstraint = findViewById(R.id.loginConstraint)
    }

    override fun onClick(v: View?) {
        when(v?.getId()) {
            R.id.loginConstraint -> {
                val inm: InputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            }
            R.id.login -> {
                if (username.text.toString() != ""){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                else{ Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show()
                }
            }
            else -> {}
        }

      }
    fun signupLink(view: View) {
        val intent = Intent(this, register::class.java)
        startActivity(intent)
    }
    }


