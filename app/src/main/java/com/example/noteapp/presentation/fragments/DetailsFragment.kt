package com.example.noteapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.domain.data.Notes
import com.example.noteapp.databinding.DetailsFragmentBinding
import com.example.noteapp.presentation.viewModel.NotesViewModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailsFragment : Fragment() {

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private var selectedColor: String = "grey" // Цвет по умолчанию

    private val notesViewModule:NotesViewModule by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val monthFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
        val timeformate = SimpleDateFormat("HH:mm", Locale.getDefault())
        val currentDate = Date()

        // Форматированная строка для даты
        val formattedDate = "${monthFormat.format(currentDate)} ${timeformate.format(currentDate)}"

        binding.dateMonth.text = monthFormat.format(currentDate)
        binding.dateTime.text = timeformate.format(currentDate)

        // Настройка выбора цвета
        setupColorSelection()

        binding.actionDo.setOnClickListener {
            val title = binding.edTitle.text.toString()
            val description = binding.edDescription.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                val notes = Notes(
                    id = 0,
                    title = title,
                    description = description,
                    date = formattedDate,
                    color = selectedColor // Сохраняем выбранный цвет
                )
                notesViewModule.addNote(notes)
                findNavController().navigateUp()
            }else{
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupColorSelection() {
        // Установка начального состояния
        binding.btnWhite.isSelected = true // Цвет по умолчанию

        binding.btnWhite.setOnClickListener {
            resetColorSelection()
            binding.btnWhite.isSelected = true
            selectedColor = "white"
        }

        binding.btnGrey.setOnClickListener {
            resetColorSelection()
            binding.btnGrey.isSelected = true
            selectedColor = "grey"
        }

        binding.btnRed.setOnClickListener {
            resetColorSelection()
            binding.btnRed.isSelected = true
            selectedColor = "red"
        }
    }

    private fun resetColorSelection() {
        binding.btnWhite.isSelected = false
        binding.btnGrey.isSelected = false
        binding.btnRed.isSelected = false
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}