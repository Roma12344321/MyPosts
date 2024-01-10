package com.dev.myposts.domain

data class Post(
    val id : Int?,
    val userName : String?,
    val title:String?,
    val content : String?,
    val isItMy : Boolean
)