package com.example.domain.repository

interface AuthStatusRepository {
    fun saveAuthStatus(isAutorized:Boolean)
    fun isUserAuthorized(): Boolean
}