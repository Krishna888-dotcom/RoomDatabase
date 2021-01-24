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
import com.example.dataactivity.db.StudentDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
                CoroutineScope(Dispatchers.IO).launch {
                    var user = StudentDb.getInstance(this@login).getUserDAO().authenticate(username.text.toString(), password.text.toString())
                    if (user == null) {
                        withContext(Main) { Toast.makeText(this@login, "Invalid Credentials", Toast.LENGTH_SHORT).show() }
                    } else {
                        startActivity(Intent(this@login, MainActivity::class.java))
                    }
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


