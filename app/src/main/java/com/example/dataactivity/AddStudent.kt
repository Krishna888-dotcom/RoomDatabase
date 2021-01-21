package com.example.dataactivity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class AddStudent : AppCompatActivity(), View.OnClickListener {
    private lateinit var etfullname : EditText
    private lateinit var etage : EditText
    private lateinit var rgGender : RadioGroup
    private lateinit var etAddress : EditText
    private lateinit var btnAdd : Button
    private lateinit var constraintLayout: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)
        binding()
        constraintLayout.setOnClickListener(this)
    }

    private fun binding() {
        etfullname = findViewById(R.id.etfullname)
        etage = findViewById(R.id.etage)
        rgGender = findViewById(R.id.rgGender)
        etAddress = findViewById(R.id.etAddress)
        btnAdd = findViewById(R.id.btnAdd)
        constraintLayout = findViewById(R.id.constraintlayout)
    }

    override fun onClick(v: View?) {
        when(v?.getId()){
            R.id.constraintlayout -> {
                val inm: InputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            }
            else -> {}
        }
    }
}