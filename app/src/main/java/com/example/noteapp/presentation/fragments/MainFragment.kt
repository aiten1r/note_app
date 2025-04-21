package com.example.noteapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.domain.data.Notes
import com.example.noteappaiditek.databinding.FragmentMainBinding
import com.example.noteapp.presentation.adapters.NotesAdapter
import com.example.noteapp.presentation.viewModel.NotesViewModule
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val notesViewModule: NotesViewModule by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NotesAdapter(onItemLongClick = { notes ->
            showNotesDeletDialog(notes)
        }, onItemClick = { notes->
            val action  =  MainFragmentDirections.actionMainFragmentToDetailsFragment(
                id = notes.id,
                title = notes.title,
                description = notes.description,
                date = notes.date,
                color = notes.color
            )
            findNavController().navigate(action)
        })
        binding.rvMain.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                notesViewModule.notesState.collect { notes ->
                    Log.d("NotesViewModel", "Received notes: $notes") // Добавить лог для отладки
                    adapter.submitList(notes)
                }
            }
        }

        binding.fab.setOnClickListener {
           val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(
               id = 0,
               title = "",
               description = "",
               date = "",
               color = "grey"
           )
            findNavController().navigate(action)
        }

    }

    private fun showNotesDeletDialog(notes: Notes) {
        AlertDialog.Builder(requireContext())
            .setTitle("Удалить заметку")
            .setMessage("Вы точно хотите удалить заметку!")
            .setPositiveButton("Удалить") { _, _ ->
                notesViewModule.deletNote(notes)
            }
            .setNegativeButton("Отмена", null)
            .create()
            .show()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}