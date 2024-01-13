package com.dev.myposts.data

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("api/v1/postlist/")
    suspend fun getPost(): List<PostDto>

    @GET("api/v1/user/")
    suspend fun getUserDtoByName(@Query("name") name: String): UserDto

    @POST("api/v1/user/")
    suspend fun register(
        @Body registrationData : RegistrationData
    ) : SuccessData

    @POST("api/v1/postlist/")
    suspend fun createPost(
        @Body createPostData: CreatePostData
    ) : SuccessData

    @POST("api/v1/postlist/delete/")
    suspend fun deletePost(
        @Body deletePostData : DeletePostData
    ) : SuccessData
}