package com.example.domain.usecase

import com.example.domain.repository.AuthStatusRepository

class IsUserAuthorizedUseCase(private val repo : AuthStatusRepository) {
    operator fun invoke(): Boolean = repo.isUserAuthorized()
}