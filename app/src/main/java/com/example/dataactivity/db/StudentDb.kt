package com.example.dataactivity.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.dataactivity.dao.StudentDAO
import com.example.dataactivity.dao.UserDAO
import com.example.dataactivity.entity.User

@Database(
        entities = [(User::class)],
        version = 1
)
abstract class StudentDb : RoomDatabase() {
    abstract fun getUserDAO() : UserDAO
    abstract fun getStudentDAO() : StudentDAO

    companion object {
        @Volatile
        private var instance: StudentDb? = null

        fun getInstance(context: Context): StudentDb {
            if (instance == null) {
                synchronized(StudentDb::class) {
                    instance = buildDatabase(context)

                }
            }
            return instance!!
        }

        private fun buildDatabase(context: Context): StudentDb {
            return databaseBuilder(
                    context.applicationContext,
                    StudentDb::class.java,
                    "StudentDB"
            ).build()
        }


    }
}