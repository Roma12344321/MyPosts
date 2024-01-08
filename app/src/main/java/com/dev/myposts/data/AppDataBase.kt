package com.dev.myposts.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserFromDb::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun userFromDbDao() : UserFromDbDao

    companion object {
        private var INSTANCE: AppDataBase? = null
        private val LOCK = Any()

        fun getInstance(application: Application): AppDataBase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
            }
            val db = Room.databaseBuilder(application,AppDataBase::class.java,"users.db")
                .build()
            INSTANCE = db
            return db
        }
    }
}