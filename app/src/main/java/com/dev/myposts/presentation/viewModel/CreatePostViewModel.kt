package com.dev.myposts.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.myposts.domain.CreatePostUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreatePostViewModel @Inject constructor(
    private val createPostUseCase: CreatePostUseCase,
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.Main)


    private val _isPostCreated = MutableLiveData<Boolean>()
    val isPostCreated: LiveData<Boolean>
        get() = _isPostCreated


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