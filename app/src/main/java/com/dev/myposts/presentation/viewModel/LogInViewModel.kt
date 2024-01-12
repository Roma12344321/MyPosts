package com.dev.myposts.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.myposts.data.ApiFactory
import com.dev.myposts.domain.CreatePostUseCase
import com.dev.myposts.domain.Post
import com.dev.myposts.domain.GetAllPostsUseCase
import com.dev.myposts.domain.LogInUseCase
import com.dev.myposts.domain.LogOutUseCase
import com.dev.myposts.domain.ReLoginUseCase
import com.dev.myposts.domain.RegisterUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class LogInViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase,
    private val reLoginUseCase: ReLoginUseCase,
) : ViewModel() {

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean>
        get() = _isLoggedIn

    private val scope = CoroutineScope(Dispatchers.Main)

    fun reLogin() {
        scope.launch {
            try {
                _isLoggedIn.value = reLoginUseCase.reLogin()
            } catch (_: Exception) {
                _isLoggedIn.value = false
            }
        }
    }

    fun logIn(userName: String, password: String) {
        scope.launch {
            _isLoggedIn.value = logInUseCase.logIn(userName, password)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}