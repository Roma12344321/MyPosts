package com.dev.myposts.domain

interface Repository {
    suspend fun logIn(userName:String,password:String) : Boolean
    suspend fun getAllPosts() : List<Post>
    suspend fun reLogin() : Boolean
    suspend fun logOut() : Boolean
    suspend fun register(username:String,email:String,password : String) : Boolean
    suspend fun createPost(title:String,content : String) : Boolean
    suspend fun deletePost(id:Int) : Boolean
}