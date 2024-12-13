package com.example.data.repositoryImpl

import com.example.data.dao.NotesDao
import com.example.data.mappers.toDomainModel
import com.example.data.mappers.toEntityModel
import com.example.domain.data.Notes
import com.example.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotesRepositoryImpl(private val notesDao: NotesDao) : NotesRepository {

    override fun getAllNotes(): Flow<List<Notes>> {
        return notesDao.getAllNotes().map { entities->
            entities.map { it.toDomainModel() }
        }
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