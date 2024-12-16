package com.example.data.repositoryImpl

import com.example.data.dao.NotesDao
import com.example.data.mappers.toDomainModel
import com.example.data.mappers.toEntityModel
import com.example.domain.data.Notes
import com.example.domain.repository.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class NotesRepositoryImpl(private val notesDao: NotesDao) : NotesRepository {

    override fun getAllNotes(): Flow<List<Notes>> = notesDao.getAllNotes().map { entities ->
        entities.map { it.toDomainModel() }
    }


    override suspend fun insertNotes(notes: Notes) {
        notesDao.insertNotes(notes.toEntityModel())
    }

    override suspend fun deletNotes(notes: Notes) {
        notesDao.deletNotes(notes.toEntityModel())
    }

    override suspend fun updateNotes(notes: Notes) {
        notesDao.updateNotes(notes.toEntityModel())
    }
}