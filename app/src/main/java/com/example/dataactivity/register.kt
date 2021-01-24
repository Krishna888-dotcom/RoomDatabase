package com.example.dataactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.dataactivity.db.StudentDb
import com.example.dataactivity.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class register : AppCompatActivity(), View.OnClickListener {
    private lateinit var fname : EditText
    private lateinit var lname : EditText
    private lateinit var etusername : EditText
    private lateinit var etpassword : EditText
    private lateinit var etcpassword : EditText
    private lateinit var registerbtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding()
        registerbtn.setOnClickListener(this)
    }

    private fun binding() {
        fname = findViewById(R.id.etfname)
        lname = findViewById(R.id.etlname)
        etusername = findViewById(R.id.etun)
        etpassword = findViewById(R.id.etpw)
        etcpassword = findViewById(R.id.etcpw)
        registerbtn = findViewById(R.id.btnSubmit)
    }

    fun loginLink(view: View) {
        val intent = Intent(this, login::class.java)
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        val fname = fname.text.toString()
        val lname = lname.text.toString()
        val username = etusername.text.toString()
        val password = etpassword.text.toString()
        val confirmPassword = etcpassword.text.toString()

        if (password != confirmPassword) {
            etpassword.error = "Password does not match"
            etpassword.requestFocus()
            return
        } else {
            // code goes here
            val user = User(fname, lname, username, password)
            CoroutineScope(Dispatchers.IO).launch {
                StudentDb
                        .getInstance(this@register)
                        .getUserDAO()
                        .registerUser(user)
                //Switch to main thread
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@register, "User Saved", Toast.LENGTH_SHORT)
                            .show()
                }
            }

        }
    }
}