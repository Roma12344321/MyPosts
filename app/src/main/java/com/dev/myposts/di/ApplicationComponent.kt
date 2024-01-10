package com.dev.myposts.di

import android.app.Application
import com.dev.myposts.presentation.activity.LogInActivity
import com.dev.myposts.presentation.fragments.MainFragment
import com.dev.myposts.presentation.activity.RegisterActivity
import com.dev.myposts.presentation.fragments.CreatePostFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class,ViewModelModule::class])
interface ApplicationComponent {
    fun inject(activity: LogInActivity)
    fun inject(fragment: MainFragment)
    fun inject(activity: RegisterActivity)
    fun inject(fragment: CreatePostFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}