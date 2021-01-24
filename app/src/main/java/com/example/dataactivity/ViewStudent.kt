package com.example.dataactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dataactivity.entity.Student
import com.example.dataactivity.studentAdapter.StudentAdapter

class ViewStudent : AppCompatActivity() {
    private var lstStudent = ArrayList<Student>()
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_student)

        recyclerView =findViewById(R.id.studentView)
        val adapter = StudentAdapter(lstStudent, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}