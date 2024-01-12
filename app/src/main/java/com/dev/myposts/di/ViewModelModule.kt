package com.dev.myposts.di

import androidx.lifecycle.ViewModel
import com.dev.myposts.presentation.viewModel.CreatePostViewModel
import com.dev.myposts.presentation.viewModel.LogInViewModel
import com.dev.myposts.presentation.viewModel.MainViewModel
import com.dev.myposts.presentation.viewModel.RegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LogInViewModel::class)
    fun bindLogInViewModel(viewModel: LogInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun binMainViewModel(viewModel: MainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    fun bindRegistrationViewModel(viewModel: RegistrationViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreatePostViewModel::class)
    fun bindCreatePostViewModel(viewModel: CreatePostViewModel) : ViewModel
}