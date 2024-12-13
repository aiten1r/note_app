package com.example.domain.usecase

import com.example.domain.data.Notes
import com.example.domain.repository.NotesRepository

class InsertNotesUseCase(private val repository: NotesRepository) {
    suspend operator fun invoke(notes: Notes){
        repository.insertNotes(notes)
    }
}