package com.example.data.repositoryImpl

import android.content.Context
import com.example.data.dao.LocalAuthDataSource

class LocalAuthDataSourceImpl(context: Context) : LocalAuthDataSource {
    private val prefs = context.getSharedPreferences("auths_prefs", Context.MODE_PRIVATE)

    override fun saveAuthStatus(isAuthorized: Boolean) {
        prefs.edit().putBoolean("isAuthorized", isAuthorized).apply()
    }

    override fun isUserAuthorized(): Boolean {
        return prefs.getBoolean("isAuthorized", false)
    }
}