package com.dev.myposts.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserFromDbDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(userFromDb: UserFromDb)

    @Query("DELETE FROM usersFromDb")
    suspend fun deleteAllUser()
}