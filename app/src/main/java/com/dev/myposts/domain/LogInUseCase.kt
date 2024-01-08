package com.dev.myposts.domain

import javax.inject.Inject

class LogInUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun logIn(userName:String,password:String) : Boolean {
        return repository.logIn(userName, password)
    }
}