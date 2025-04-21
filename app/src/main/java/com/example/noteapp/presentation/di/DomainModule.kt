package com.example.noteapp.presentation.di

import com.example.domain.usecase.DeletNotesUseCase
import com.example.domain.usecase.GetAllNotesUseCase
import com.example.domain.usecase.InsertNotesUseCase
import com.example.domain.usecase.IsUserAuthorizedUseCase
import com.example.domain.usecase.LoginUseCase
import com.example.domain.usecase.RegistrationUseCase
import com.example.domain.usecase.SaveAuthStatusUseCase
import com.example.domain.usecase.UpdateNotesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { DeletNotesUseCase(get()) }
    factory { InsertNotesUseCase(get()) }
    factory { UpdateNotesUseCase(get()) }
    factory { GetAllNotesUseCase(get()) }
    factory { RegistrationUseCase(get()) }
    factory { LoginUseCase(get()) }
    factory { SaveAuthStatusUseCase(get()) }
    factory { IsUserAuthorizedUseCase(get()) }
}