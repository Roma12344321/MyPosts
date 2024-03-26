package com.dev.myposts.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.dev.myposts.data.ApiFactory
import com.dev.myposts.data.ApiService
import com.dev.myposts.data.AppDataBase
import com.dev.myposts.data.RepositoryImpl
import com.dev.myposts.data.UserFromDb
import com.dev.myposts.data.UserFromDbDao
import com.dev.myposts.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface DataModule {

    @Singleton
    @Binds
    fun bindRepositoryImpl(impl : RepositoryImpl) : Repository

    companion object {

        @Singleton
        @Provides
        fun provideApiService() : ApiService {
            return ApiFactory.apiService
        }

        @Singleton
        @Provides
        fun provideUserFromDbDao(application: Application) : UserFromDbDao {
            return AppDataBase.getInstance(application).userFromDbDao()
        }

        @Singleton
        @Provides
        fun provideSharedPreferences(application: Application) : SharedPreferences {
            return application.getSharedPreferences("default_prefs", Context.MODE_PRIVATE)
        }

    }
}