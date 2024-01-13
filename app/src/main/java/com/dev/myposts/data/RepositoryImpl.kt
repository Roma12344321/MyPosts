package com.dev.myposts.data

import com.dev.myposts.domain.Post
import com.dev.myposts.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: Mapper,
    private val userFromDbDao: UserFromDbDao
) : Repository {

    override suspend fun register(username: String, email: String, password: String): Boolean {
        apiService.register(RegistrationData(username, email, password))
        return true
    }

    override suspend fun logIn(userName: String, password: String): Boolean {
        val userDto = apiService.getUserDtoByName(userName)
        return if (userDto.password == password) {
            userFromDbDao.deleteAllUser()
            userFromDbDao.addUser(mapper.mapUserDtoToUserDbModel(userDto))
            true
        } else {
            false
        }
    }

    override suspend fun reLogin(): Boolean {
        val userFromDb = userFromDbDao.getUserFromDbById(UserFromDb.ID) ?: return false
        return userFromDb.userName == apiService.getUserDtoByName(userFromDb.userName).userName
    }

    override suspend fun logOut(): Boolean {
        userFromDbDao.deleteAllUser()
        return true
    }

    override suspend fun createPost(title: String, content: String): Boolean {
        val userFromDb = userFromDbDao.getUserFromDbById(UserFromDb.ID)
        apiService.createPost(CreatePostData(title, content, userFromDb!!.userName))
        return true
    }

    override suspend fun getAllPosts(): List<Post> {
        return apiService.getPost().map {
            mapper.mapPostDtoToEntity(it)
        }
    }
}