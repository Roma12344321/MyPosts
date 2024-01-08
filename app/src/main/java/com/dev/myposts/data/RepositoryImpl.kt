package com.dev.myposts.data

import android.util.Log
import com.dev.myposts.domain.Post
import com.dev.myposts.domain.Repository
import java.lang.Exception
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: Mapper,
    private val userFromDbDao: UserFromDbDao
) : Repository {

    override suspend fun logIn(userName: String, password: String): Boolean {
        try {
            val userDto = apiService.getUserDtoByName(userName)
            if (userDto.password == password){
                userFromDbDao.addUser(mapper.mapUserDtoToUserDbModel(userDto))
                return true
            }
            else {
                return false
            }
        } catch (_:Exception) {
            return false
        }
    }

    override suspend fun getAllPosts(): List<Post> {
        return apiService.getPost().map {
            mapper.mapPostDtoToEntity(it)
        }
    }
}