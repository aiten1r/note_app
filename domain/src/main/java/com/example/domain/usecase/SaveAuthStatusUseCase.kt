package com.example.domain.usecase

import com.example.domain.repository.AuthStatusRepository

class SaveAuthStatusUseCase(private val repo: AuthStatusRepository) {
    operator fun invoke(isAuthorized : Boolean) = repo.saveAuthStatus(isAuthorized)
}