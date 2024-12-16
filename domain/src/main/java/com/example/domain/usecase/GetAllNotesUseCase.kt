package com.example.domain.usecase

import com.example.domain.data.Notes
import com.example.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class GetAllNotesUseCase(private val repository:NotesRepository) {
    operator fun invoke():Flow<List<Notes>>{
        return repository.getAllNotes()
    }
}