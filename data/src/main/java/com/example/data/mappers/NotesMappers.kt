package com.example.data.mappers

import com.example.data.entity.NotesEntity
import com.example.domain.data.Notes

fun NotesEntity.toDomainModel(): Notes {
    return Notes(
        id = this.id,
        title = this.title,
        description = this.description,
        date = this.date,
        color = this.color
    )
}

// Преобразование из Notes в NotesEntity
fun Notes.toEntityModel(): NotesEntity {
    return NotesEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        date = this.date,
        color = this.color
    )
}