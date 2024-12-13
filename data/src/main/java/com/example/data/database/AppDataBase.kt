package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.dao.NotesDao
import com.example.data.entity.NotesEntity

@Database(entities = [NotesEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}