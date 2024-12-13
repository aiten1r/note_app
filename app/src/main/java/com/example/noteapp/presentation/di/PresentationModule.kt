package com.example.noteapp.presentation.di

import com.example.noteapp.presentation.viewModel.NotesViewModule
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { NotesViewModule(get()) }
}