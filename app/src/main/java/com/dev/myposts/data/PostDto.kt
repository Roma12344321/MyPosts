package com.dev.myposts.data

import com.google.gson.annotations.SerializedName

data class PostDto(
    @SerializedName("id")
    val id : Int,
    @SerializedName("title")
    val title:String?,
    @SerializedName("content")
    val content : String?,
    @SerializedName("user_details")
    val userDetails: UserDetails
)