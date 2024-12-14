package com.example.noteapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.domain.data.Notes
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentMainBinding
import com.example.noteapp.presentation.adapters.NotesAdapter
import com.example.noteapp.presentation.viewModel.NotesViewModule
import kotlinx.coroutines.flow.collectLatest
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
        }, onItemClick = { notes ->
            val bundle = Bundle().apply {
                putInt("id", notes.id)
                putString("title", notes.title)
                putString("description", notes.description)
                putString("date", notes.date)
                putString("color", notes.color)
            }
            findNavController().navigate(R.id.action_mainFragment_to_detailsFragment, bundle)
        })
        binding.rvMain.adapter = adapter

        lifecycleScope.launch {
            notesViewModule.notesFlow.collectLatest { notes ->
                if (notes.isNotEmpty()){
                    Log.d("MainFragment", "Notes collected: $notes")
                    adapter.submitList(notes)
                }
            }
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_detailsFragment)
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