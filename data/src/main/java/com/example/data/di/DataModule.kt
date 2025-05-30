package com.example.data.di

import androidx.room.Room
import com.example.data.dao.LocalAuthDataSource
import com.example.data.database.AppDataBase
import com.example.data.repositoryImpl.AuthRepositoryImpl
import com.example.data.repositoryImpl.AuthStatusRepositoryImpl
import com.example.data.repositoryImpl.LocalAuthDataSourceImpl
import com.example.data.repositoryImpl.NotesRepositoryImpl
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.AuthStatusRepository
import com.example.domain.repository.NotesRepository
import org.koin.dsl.module

val dataModule = module {

    // База данных Room
    single {
        Room.databaseBuilder(
            get(),
            AppDataBase::class.java,
            "notes_database"
        ).fallbackToDestructiveMigration().build()
    }

    // DAO
    single { get<AppDataBase>().notesDao() }

    // Репозиторий
    single<NotesRepository> { NotesRepositoryImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl() }

    // SharedPreferences
    single<LocalAuthDataSource> { LocalAuthDataSourceImpl(get()) }
    single<AuthStatusRepository> { AuthStatusRepositoryImpl(get()) }
}
