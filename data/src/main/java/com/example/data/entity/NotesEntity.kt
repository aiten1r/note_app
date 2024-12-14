package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NotesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val color: String
)
