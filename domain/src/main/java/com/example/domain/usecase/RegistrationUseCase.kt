package com.example.domain.usecase

import com.example.domain.repository.AuthRepository

class RegistrationUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email:String,password:String):Result<Unit>{
        return authRepository.register(email,password)
    }
}