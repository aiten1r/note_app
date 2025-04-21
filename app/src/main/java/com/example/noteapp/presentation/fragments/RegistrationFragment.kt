package com.example.noteapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.noteapp.presentation.viewModel.AuthViewModel
import com.example.noteappaiditek.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.noteappaiditek.databinding.RegistrationFragmentBinding

class RegistrationFragment : Fragment() {
    private var _binding: RegistrationFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AuthViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegistrationFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegistration.setOnClickListener {
            val email = binding.edEmail.text.toString().trim()
            val paswword = binding.edPassword.text.toString().trim()
            viewModel.register(email, paswword)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.edEmail.text.toString().trim()
            val paswword = binding.edPassword.text.toString().trim()
            viewModel.login(email, paswword)
        }

        observViewModel()

    }

    private fun observViewModel() {
        viewModel.authState.observe(viewLifecycleOwner) { result ->
            result.fold(
                onSuccess = {
                    Toast.makeText(requireContext(), "Успех!", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_registrationFragment_to_mainFragment)
                },
                onFailure = {
                    Toast.makeText(requireContext(), "Ошибка: ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}