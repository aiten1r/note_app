package com.example.noteapp.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.data.Notes
import com.example.domain.repository.NotesRepository
import com.example.domain.usecase.DeletNotesUseCase
import com.example.domain.usecase.GetAllNotesUseCase
import com.example.domain.usecase.InsertNotesUseCase
import com.example.domain.usecase.UpdateNotesUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotesViewModule(
    private val deletNotesUseCase: DeletNotesUseCase,
    private val insertNotesUseCase: InsertNotesUseCase,
    private val updateNotesUseCase: UpdateNotesUseCase,
    private val getAllNotesUseCase: GetAllNotesUseCase
):ViewModel() {

    // Поток заметок
    val notesFlow: StateFlow<List<Notes>> = getAllNotesUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        viewModelScope.launch {
            notesFlow.collect { notes ->
                Log.d("NotesViewModule", "Notes loaded: $notes")
            }
        }
    }

    fun addNote(notes: Notes){
        viewModelScope.launch {
            Log.d("NotesViewModule", "Adding note: $notes")
            insertNotesUseCase(notes)
        }
    }

    fun deletNote(notes: Notes){
        viewModelScope.launch {
            deletNotesUseCase(notes)
        }
    }

    fun updateNote(notes: Notes){
        viewModelScope.launch {
            updateNotesUseCase(notes)
        }
    }

}