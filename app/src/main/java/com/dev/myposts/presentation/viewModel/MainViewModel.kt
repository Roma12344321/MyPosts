package com.dev.myposts.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.myposts.domain.CreatePostUseCase
import com.dev.myposts.domain.DeletePostUseCase
import com.dev.myposts.domain.GetAllPostsUseCase
import com.dev.myposts.domain.LogOutUseCase
import com.dev.myposts.domain.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getAllPostsUseCase: GetAllPostsUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val deletePostUseCase: DeletePostUseCase
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.Main)

    private val _isLoggedOut = MutableLiveData<Boolean>()
    val isLoggedOut: LiveData<Boolean>
        get() = _isLoggedOut

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>>
        get() = _posts

    private val _showProgressBar = MutableLiveData<Boolean>()
    val showProgressBar: LiveData<Boolean>
        get() = _showProgressBar

    private val _isPostDeleted = MutableLiveData<Boolean>()
    val isPostDeleted: LiveData<Boolean>
        get() = _isPostDeleted

    fun getAllPosts() {
        scope.launch {
            _showProgressBar.value = true
            _posts.value = getAllPostsUseCase.getAllPost()
            _showProgressBar.value = false
        }
    }

    fun deletePost(id:Int) {
        scope.launch {
            try {
                _isPostDeleted.value = deletePostUseCase.deletePost(id)
            } catch (_:Exception) {
                _isPostDeleted.value = false
            }
        }
    }

    fun logOut() {
        scope.launch {
            _isLoggedOut.value = logOutUseCase.logOut()
        }
    }
    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}