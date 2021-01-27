package com.example.dataactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dataactivity.db.StudentDb
import com.example.dataactivity.entity.Student
import com.example.dataactivity.studentAdapter.StudentAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewStudent : AppCompatActivity() {
    private var lstStudent :List<Student> = listOf()
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_student)

        recyclerView =findViewById(R.id.studentView)

        CoroutineScope(Dispatchers.IO).launch {
            lstStudent = StudentDb.getInstance(this@ViewStudent).getStudentDAO().findAll()

            withContext(Main)
            {
                val adapter = StudentAdapter(this@ViewStudent, lstStudent.toMutableList())
                recyclerView.layoutManager = LinearLayoutManager(this@ViewStudent)
                recyclerView.adapter = adapter
            }
        }




    }
}