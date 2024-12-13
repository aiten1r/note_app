package com.example.noteapp.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.data.Notes
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.databinding.MainItemBinding

class NotesAdapter(
    private val onItemClick: (Notes) -> Unit
) : ListAdapter<Notes, NotesAdapter.ViewHolder>(NotesDiffUtilCallBack()) {
    class ViewHolder(private val binding: MainItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun Bind(
            notes: Notes,
            onItemClick: (Notes) -> Unit
        ){

        }
    }

    private class NotesDiffUtilCallBack {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}