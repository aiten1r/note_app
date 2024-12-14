package com.example.noteapp.presentation.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.data.Notes
import com.example.noteapp.R
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.databinding.MainItemBinding

class NotesAdapter(
    private val onItemLongClick: (Notes) -> Unit,
    private val onItemClick: (Notes) -> Unit
) : ListAdapter<Notes, NotesAdapter.ViewHolder>(NotesDiffUtilCallBack()) {
    class ViewHolder(private val binding: MainItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            notes: Notes,
            onItemLongClick: (Notes) -> Unit,
            onItemClick: (Notes) -> Unit
        ){
            binding.tvTitle.text = notes.title
            binding.tvDescription.text = notes.description
            binding.tvDate.text = notes.date
            binding.root.setBackgroundColor(
                when(notes.color){
                    "white" -> R.color.btnWhiteColor
                    "grey"-> R.color.btnGreyColor
                    "red"->R.color.btnRedColor
                    else -> R.color.btnGreyColor
                }
            )
            binding.root.setOnLongClickListener{
                onItemLongClick(notes)
                true
            }
            binding.root.setOnClickListener {
                onItemClick(notes)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MainItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position),onItemLongClick,onItemClick)
    }

    private class NotesDiffUtilCallBack:DiffUtil.ItemCallback<Notes>() {
        override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
           return oldItem == newItem
        }

    }
}