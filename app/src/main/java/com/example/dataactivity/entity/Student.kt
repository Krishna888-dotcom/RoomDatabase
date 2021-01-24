package com.example.dataactivity.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student (
        var stdName : String? = null,
        var stdAge : Int? = null,
        var stdGender : String? = null,
        var stdAddress : String? = null
        ){
    @PrimaryKey(autoGenerate = true)
    var stdID : Int = 0
}