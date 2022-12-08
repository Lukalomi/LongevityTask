package com.example.longevitytask.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.longevitytask.databinding.FragmentAuthorizationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Authorization : Fragment() {

    private var binding: FragmentAuthorizationBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navToRegister()
        navToLogin()
    }

    private fun navToRegister() {
        binding!!.btnRegister.setOnClickListener{
            findNavController().navigate(AuthorizationDirections.actionAuthorizationToRegister())
        }
    }

    private fun navToLogin() {
        binding!!.btnLogin.setOnClickListener{
            findNavController().navigate(AuthorizationDirections.actionAuthorizationToLogin())
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}