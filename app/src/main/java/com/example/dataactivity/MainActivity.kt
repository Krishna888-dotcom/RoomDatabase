package com.example.dataactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var StudentAdd : Button
    private lateinit var StudentView : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding()
        StudentAdd.setOnClickListener(this)
        StudentView.setOnClickListener(this)
    }

    private fun binding() {
        StudentAdd = findViewById(R.id.Addbutton)
        StudentView = findViewById(R.id.Viewbutton)
    }

    override fun onClick(v: View?) {
        when(v?.getId()){
            R.id.Addbutton -> {
                val intent = Intent(this, AddStudent::class.java)
                startActivity(intent)
            }
            R.id.Viewbutton -> {
                startActivity(Intent(this, ViewStudent::class.java))
            }
            else -> {}
        }
    }
}