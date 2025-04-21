package com.example.data.repositoryImpl

import com.example.data.dao.LocalAuthDataSource
import com.example.domain.repository.AuthStatusRepository

class AuthStatusRepositoryImpl(private val localAuthDataSource: LocalAuthDataSource) : AuthStatusRepository {
    override fun saveAuthStatus(isAutorized: Boolean) {
        localAuthDataSource.saveAuthStatus(isAutorized)
    }

    override fun isUserAuthorized(): Boolean {
        return localAuthDataSource.isUserAuthorized()
    }

}