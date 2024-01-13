package com.dev.myposts.di

import android.app.Application
import com.dev.myposts.presentation.fragments.HomeFragment
import com.dev.myposts.presentation.fragments.CreatePostFragment
import com.dev.myposts.presentation.fragments.LogInFragment
import com.dev.myposts.presentation.fragments.PersonFragment
import com.dev.myposts.presentation.fragments.RegisterFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class,ViewModelModule::class])
interface ApplicationComponent {
    fun inject(fragment: LogInFragment)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: CreatePostFragment)
    fun inject(fragment: RegisterFragment)
    fun inject(fragment : PersonFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}