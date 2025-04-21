package com.example.noteapp.presentation.di

import com.example.noteapp.presentation.viewModel.AuthViewModel
import com.example.noteapp.presentation.viewModel.NotesViewModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        NotesViewModule(
            deletNotesUseCase = get(),
            insertNotesUseCase = get(),
            updateNotesUseCase = get(),
            getAllNotesUseCase = get()
        )
    }
    viewModel {
        AuthViewModel(
            registrationUseCase = get(),
            loginUseCase = get(),
            saveAuthStatusUsceCase = get(),
            isUserAuthorizedUseCase = get()
        )
    }
}