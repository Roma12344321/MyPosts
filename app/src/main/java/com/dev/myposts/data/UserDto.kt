package com.dev.myposts.data

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("id")
    val id : Int,
    @SerializedName("username")
    val userName : String,
    @SerializedName("email")
    val email : String,
    @SerializedName("password")
    val password : String,
    @SerializedName("is_active")
    val isActive : Boolean
)
