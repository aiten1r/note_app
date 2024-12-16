package com.example.noteapp.presentation.adapters

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
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
                ContextCompat.getColor(binding.root.context,when(notes.color){
                    "white" -> R.color.btnWhiteColor
                    "grey"-> R.color.btnGreyColor
                    "red"->R.color.btnRedColor
                    else -> R.color.btnGreyColor
                })
            )

            binding.root.post{
                setRoundedCorners(binding.root, 16f)
            }

            binding.root.setOnLongClickListener{
                onItemLongClick(notes)
                true
            }
            binding.root.setOnClickListener {
                onItemClick(notes)
            }

            // Обновляем цвета текста в зависимости от фона
            updateTextColorBasedOnBackgroundColor(binding.root, binding.tvTitle, notes.color)
            updateTextColorBasedOnBackgroundColor(binding.root, binding.tvDescription, notes.color)
            updateTextColorBasedOnBackgroundColor(binding.root, binding.tvDate, notes.color)

        }

        private fun setRoundedCorners(view: View,radius:Float) {
            val drawable = GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                cornerRadius = radius
                setColor((view.background as ColorDrawable).color)
            }
            view.background = drawable
        }

        private fun updateTextColorBasedOnBackgroundColor(view: View, textView: TextView, color: String) {
            val textColor = when (color) {
                "white" -> ContextCompat.getColor(view.context, R.color.textColorWhite)
                "grey" -> ContextCompat.getColor(view.context, R.color.textColorGrey)
                "red" -> ContextCompat.getColor(view.context, R.color.textColorRed)
                else -> ContextCompat.getColor(view.context, R.color.textColorGrey)
            }
            textView.setTextColor(textColor)
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

    override fun submitList(list: List<Notes>?) {
        Log.d("NotesViewModel", "New list submitted: $list")
        super.submitList(list)
    }
}