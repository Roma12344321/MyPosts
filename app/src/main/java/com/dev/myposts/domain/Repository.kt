package com.dev.myposts.domain

interface Repository {
    suspend fun logIn(userName:String,password:String) : Boolean
    suspend fun getAllPosts() : List<Post>
}