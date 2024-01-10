package com.dev.myposts.domain

import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun register(username: String, email: String, password: String) : Boolean {
        return repository.register(username, email, password)
    }
}