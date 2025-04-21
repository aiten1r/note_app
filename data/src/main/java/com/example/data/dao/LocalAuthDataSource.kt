package com.example.data.dao

interface LocalAuthDataSource {
    fun saveAuthStatus(isAuthorized : Boolean)
    fun isUserAuthorized() : Boolean
}