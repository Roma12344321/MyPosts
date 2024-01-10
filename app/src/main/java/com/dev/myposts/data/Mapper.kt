package com.dev.myposts.data

import com.dev.myposts.domain.Post
import javax.inject.Inject

class Mapper @Inject constructor(
    private val userFromDbDao: UserFromDbDao
) {

    suspend fun mapPostDtoToEntity(postDto: PostDto): Post {
        return Post(
            id = postDto.id,
            userName = postDto.userDetails.username,
            title = postDto.title,
            content = postDto.content,
            isItMy = userFromDbDao.getUserFromDbById(UserFromDb.ID)?.userName == postDto.userDetails.username
        )
    }
    fun mapUserDtoToUserDbModel(userDto: UserDto) : UserFromDb {
        return UserFromDb(
            userName = userDto.userName,
            email = userDto.email,
            password = userDto.password
        )
    }
}