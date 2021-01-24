package com.example.dataactivity.dao

import androidx.room.*
import com.example.dataactivity.entity.Student
import kotlinx.coroutines.selects.select

@Dao
interface StudentDAO {
    //insert user
    @Insert
    suspend fun addStudent(student: Student)

    @Query("select * from Student")
    suspend fun findAll() : List<Student>

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

}