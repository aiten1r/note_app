package com.example.noteapp.presentation.app

import android.app.Application
import com.example.data.di.dataModule
import com.example.noteapp.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // Инициализация Koin
        startKoin {
            androidContext(this@App) // Передаем ApplicationContext
            modules(listOf(dataModule, presentationModule)) // Подключаем модули
        }

    }
}