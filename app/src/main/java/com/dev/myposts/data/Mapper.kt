package com.dev.myposts.data

import com.dev.myposts.domain.Post
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun mapPostDtoToEntity(postDto: PostDto): Post {
        return Post(
            userName = postDto.userDetails.username,
            title = postDto.title,
            content = postDto.content
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