package com.dev.myposts.domain

import javax.inject.Inject

class DeletePostUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun deletePost(id:Int) : Boolean{
        return repository.deletePost(id)
    }
}