package com.dev.myposts.domain

import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val repository : Repository
) {
    suspend fun logOut() : Boolean {
        return repository.logOut()
    }
}