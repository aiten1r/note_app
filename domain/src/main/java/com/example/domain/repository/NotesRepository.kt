package com.example.domain.repository

import com.example.domain.data.Notes
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun getAllNotes(): Flow<List<Notes>>
    suspend fun insertNotes(notes: Notes)
    suspend fun deletNotes(notes: Notes)
    suspend fun updateNotes(notes: Notes)
}
