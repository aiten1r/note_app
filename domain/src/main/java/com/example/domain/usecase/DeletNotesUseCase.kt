package com.example.domain.usecase

import com.example.domain.data.Notes
import com.example.domain.repository.NotesRepository

class DeletNotesUseCase(private val repository: NotesRepository) {
    suspend operator fun invoke(notes: Notes){
        repository.deletNotes(notes)
    }
}