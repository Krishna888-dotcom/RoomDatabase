package com.example.dataactivity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.dataactivity.db.StudentDb
import com.example.dataactivity.entity.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddStudent : AppCompatActivity(), View.OnClickListener {
    private lateinit var etfullname : EditText
    private lateinit var etage : EditText
    private lateinit var rgGender : RadioGroup
    private lateinit var etAddress : EditText
    private lateinit var btnAdd : Button
    var gender = "male"
    private lateinit var constraintLayout: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)
        binding()
        radioGroupListener()
        constraintLayout.setOnClickListener(this)
        btnAdd.setOnClickListener(this)
    }

    private fun radioGroupListener() {
        rgGender.setOnCheckedChangeListener { group, checkId ->
            when (checkId) {
                R.id.rbMale -> {
                    gender = "Male"
                }
                R.id.rbFemale -> {
                    gender = "Female"
                }
                R.id.rbOther ->
                    gender = "Others"
            }
        }
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
        when(v?.id){
            R.id.constraintlayout -> {
                val inm: InputMethodManager =
                    getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            }
            R.id.btnAdd ->{
                val student = Student(etfullname.text.toString(),etage.text.toString().toInt(),gender,etAddress.text.toString())
                etfullname.text.clear()
                etage.text.clear()
                etAddress.text.clear()
                Toast.makeText(this, "Student Added successfully", Toast.LENGTH_SHORT).show()

                CoroutineScope(Dispatchers.IO).launch {
                    StudentDb.getInstance(this@AddStudent)!!.getStudentDAO().addStudent(student)
                }
            }
            else -> {}
        }
    }
}