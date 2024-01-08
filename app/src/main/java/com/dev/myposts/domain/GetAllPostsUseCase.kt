package com.dev.myposts.domain

import javax.inject.Inject

class GetAllPostsUseCase @Inject constructor(private val repository: Repository) {
    suspend fun getAllPost() : List<Post> {
        return repository.getAllPosts()
    }
}