package com.dev.myposts.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.myposts.data.ApiFactory
import com.dev.myposts.domain.Post
import com.dev.myposts.domain.GetAllPostsUseCase
import com.dev.myposts.domain.LogInUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getAllPostsUseCase: GetAllPostsUseCase,
    private val logInUseCase: LogInUseCase
) : ViewModel() {

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn : LiveData<Boolean>
        get() = _isLoggedIn

    private val _posts = MutableLiveData<List<Post>>()
    val posts : LiveData<List<Post>>
        get() = _posts

    private val scope = CoroutineScope(Dispatchers.Main)

    fun getAllPosts() {
        scope.launch {
            _posts.value = getAllPostsUseCase.getAllPost()
        }
    }

    fun logIn(userName : String,password : String) {
        scope.launch {
            _isLoggedIn.value = logInUseCase.logIn(userName,password)
        }
    }



    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}