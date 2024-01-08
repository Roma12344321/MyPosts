package com.dev.myposts.di

import android.app.Application
import android.content.Context
import com.dev.myposts.presentation.LogInActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class,ViewModelModule::class])
interface ApplicationComponent {
    fun inject(activity: LogInActivity)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}