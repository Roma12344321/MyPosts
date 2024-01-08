package com.dev.myposts.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/v1/postlist/")
    suspend fun getPost(): List<PostDto>

    @GET("api/v1/postlist/user/")
    suspend fun getUserDtoByName(@Query("name") name: String): UserDto
}