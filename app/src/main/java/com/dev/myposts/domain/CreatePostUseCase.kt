package com.dev.myposts.domain

import javax.inject.Inject

class CreatePostUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun createPost(title: String, content: String): Boolean {
        return repository.createPost(title, content)
    }
}