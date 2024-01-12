package com.dev.myposts.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.myposts.domain.RegisterUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.Main)


    private val _isRegistered = MutableLiveData<Boolean>()
    val isRegistered: LiveData<Boolean>
        get() = _isRegistered

    fun register(userName: String, email: String, password: String) {
        scope.launch {
            try {
                _isRegistered.value = registerUseCase.register(userName, email, password)
            } catch (_: Exception) {
                _isRegistered.value = false
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}