package com.example.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.data.entity.NotesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Insert
    suspend fun insertNotes(notesEntity: NotesEntity)

    @Delete
    suspend fun deletNotes(notesEntity: NotesEntity)

    @Update
    suspend fun updateNotes(notesEntity: NotesEntity)

    @Query("SELECT * FROM notes ORDER BY id ASC")
    fun getAllNotes(): Flow<List<NotesEntity>>



}