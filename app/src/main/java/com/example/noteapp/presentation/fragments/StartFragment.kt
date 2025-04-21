package com.example.noteapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.noteapp.presentation.viewModel.AuthViewModel
import com.example.noteappaiditek.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : Fragment(){

    private val viewModel: AuthViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FrameLayout(requireContext())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.isUserAuthorized()){
            findNavController().navigate(R.id.action_startFragment_to_mainFragment)
        }else{
            findNavController().navigate(R.id.action_startFragment_to_registrationFragment)
        }
    }
}