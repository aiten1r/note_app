package com.example.noteapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.data.Notes
import com.example.noteapp.R
import com.example.noteapp.databinding.DetailsFragmentBinding
import com.example.noteapp.presentation.viewModel.NotesViewModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailsFragment : Fragment() {

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val notesViewModule: NotesViewModule by viewModel()
    private val args: DetailsFragmentArgs by navArgs()

    private var selectedColor: String = "white" // Цвет по умолчанию

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

        val id = args.id
        val title = args.title
        val descripton = args.description
        val date = args.date
        val color = args.color

        if (id != 0) {

            binding.edTitle.setText(title)
            binding.edDescription.setText(descripton)
            binding.arivedText.text = date
            selectedColor = color
            setSelectedColor(color)
            binding.arivedText.visibility = View.VISIBLE
        }
        // Настройка выбора цвета
        setupColorSelection()

        val curentDate = SimpleDateFormat("dd MMMM", Locale.getDefault()).format(Date())
        binding.curentDate.text = curentDate

        binding.actionDo.setOnClickListener {
            val updateTitle = binding.edTitle.text.toString()
            val updateDescription = binding.edDescription.text.toString()

            if (updateTitle.isNotEmpty() && updateDescription.isNotEmpty()) {
                val savedDate = SimpleDateFormat("dd:MM:yy", Locale.getDefault()).format(Date())
                if (id == 0) {
                    // Создание новой заметки
                    val addNotes = Notes(
                        id = 0,
                        title = updateTitle,
                        description = updateDescription,
                        date = savedDate,
                        color = selectedColor
                    )
                    notesViewModule.addNote(addNotes)
                } else {
                    // Обновление существующей заметки
                    val updateNotes = Notes(
                        id = id,
                        title = updateTitle,
                        description = updateDescription,
                        date = savedDate,
                        color = selectedColor
                    )
                    notesViewModule.updateNote(updateNotes)
                }
                findNavController().navigateUp() // Вернуться на предыдущий экран
            } else{
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }

        binding.goToBackstep.setOnClickListener{
            findNavController().navigateUp()
        }
    }

    private fun setSelectedColor(color: String) {
        resetColorSelection()
        when (color) {
            "white" -> binding.btnWhite.isSelected = true
            "grey" -> binding.btnGrey.isSelected = true
            "red" -> binding.btnRed.isSelected = true
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