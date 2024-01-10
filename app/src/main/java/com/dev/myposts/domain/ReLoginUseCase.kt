package com.dev.myposts.domain

import javax.inject.Inject

class ReLoginUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun reLogin() : Boolean {
        return repository.reLogin()
    }
}