package com.example.dataactivity.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.dataactivity.entity.User

@Dao
interface UserDAO {
    //insert User
    @Insert
    suspend fun registerUser(user: User)
}