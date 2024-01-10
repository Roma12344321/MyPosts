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

class MainViewModel @Inject constructor(
    private val getAllPostsUseCase: GetAllPostsUseCase,
    private val logInUseCase: LogInUseCase,
    private val reLoginUseCase: ReLoginUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val registerUseCase: RegisterUseCase,
    private val createPostUseCase: CreatePostUseCase
) : ViewModel() {

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean>
        get() = _isLoggedIn

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>>
        get() = _posts

    private val _isLoggedOut = MutableLiveData<Boolean>()
    val isLoggedOut: LiveData<Boolean>
        get() = _isLoggedOut

    private val _isRegistered = MutableLiveData<Boolean>()
    val isRegistered: LiveData<Boolean>
        get() = _isRegistered

    private val _isPostCreated = MutableLiveData<Boolean>()
    val isPostCreated: LiveData<Boolean>
        get() = _isPostCreated

    private val scope = CoroutineScope(Dispatchers.Main)

    fun getAllPosts() {
        scope.launch {
            _posts.value = getAllPostsUseCase.getAllPost()
        }
    }

    fun reLogin() {
        scope.launch {
            _isLoggedIn.value = reLoginUseCase.reLogin()
        }
    }

    fun logIn(userName: String, password: String) {
        scope.launch {
            _isLoggedIn.value = logInUseCase.logIn(userName, password)
        }
    }

    fun logOut() {
        scope.launch {
            _isLoggedOut.value = logOutUseCase.logOut()
        }
    }

    fun register(userName: String, email: String, password: String) {
        scope.launch {
            _isRegistered.value = registerUseCase.register(userName, email, password)
        }
    }

    fun createPost(title: String, content: String) {
        scope.launch {
            _isPostCreated.value = createPostUseCase.createPost(title, content)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}