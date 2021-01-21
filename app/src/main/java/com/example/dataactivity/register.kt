package com.example.dataactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

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
        val intent = Intent(this, login::class.java)
        startActivity(intent)
    }
}