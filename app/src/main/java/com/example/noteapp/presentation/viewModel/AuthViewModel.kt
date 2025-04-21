package com.example.noteapp.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.IsUserAuthorizedUseCase
import com.example.domain.usecase.LoginUseCase
import com.example.domain.usecase.RegistrationUseCase
import com.example.domain.usecase.SaveAuthStatusUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val registrationUseCase: RegistrationUseCase,
    private val loginUseCase: LoginUseCase,
    private val saveAuthStatusUsceCase : SaveAuthStatusUseCase,
    private val isUserAuthorizedUseCase: IsUserAuthorizedUseCase
) : ViewModel() {

    private val _authState = MutableLiveData<Result<Unit>>()
    val authState: LiveData<Result<Unit>> = _authState

    fun isUserAuthorized(): Boolean{
        return isUserAuthorizedUseCase()
    }


    fun register(email:String,password:String){
        viewModelScope.launch {
            val result = registrationUseCase(email,password)
            if (result.isSuccess){
                saveAuthStatusUsceCase(true)
            }
            _authState.value = result
        }
    }

    fun login(email:String,password:String){
        viewModelScope.launch {
            val result = loginUseCase(email,password)
            if (result.isSuccess){
                saveAuthStatusUsceCase(true)
            }
            _authState.value = result
        }
    }
}