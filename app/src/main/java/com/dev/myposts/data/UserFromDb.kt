package com.dev.myposts.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "usersFromDb")
data class UserFromDb(
    @PrimaryKey
    val id : Int = ID,
    val userName : String,
    val email : String,
    val password : String,
){
    @Ignore
    constructor(userName: String,email: String,password: String) : this(ID,userName,email,password)
    companion object{
        private const val ID = 23
    }
}