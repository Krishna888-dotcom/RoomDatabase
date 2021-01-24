package com.example.dataactivity.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dataactivity.entity.User

@Dao
interface UserDAO {
    //insert User
    @Insert
    suspend fun registerUser(user: User)

    @Query("select * from User where username = :un and password = :pw")
    suspend fun authenticate(un:String,pw:String):User
}
