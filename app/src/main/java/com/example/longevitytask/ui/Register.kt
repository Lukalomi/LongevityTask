package com.example.longevitytask.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.longevitytask.R
import com.example.longevitytask.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



@AndroidEntryPoint
class Register : Fragment() {

    private var binding: FragmentRegisterBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.btnRegister.setOnClickListener{
            registerUser()

        }


    }

    private fun registerUser() {
        checkFields()
        if (binding!!.etEmail.text.toString().isNotEmpty() && binding!!.etPass.text.toString()
                .isNotEmpty()
        ) {

            viewLifecycleOwner.lifecycleScope.launch {

                try {
                    binding!!.pgRegister.visibility = View.VISIBLE
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        binding!!.etEmail.text.toString(),
                        binding!!.etPass.text.toString()
                    ).addOnCompleteListener {

                        if(it.isSuccessful) {
                            checkLoggedInstance()
                            binding!!.pgRegister.visibility = View.GONE

                        }
                        else {
                            Toast.makeText(requireContext(),it.exception.toString(),Toast.LENGTH_LONG).show()
                            binding!!.pgRegister.visibility = View.GONE

                        }
                    }


                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
                    }

                }

            }
        }
    }
    private fun checkFields() {
        if (binding!!.etEmail.text.toString().isEmpty() && binding!!.etPass.text.toString()
                .isEmpty())
        {
            Toast.makeText(requireContext(), getString(R.string.fill_every_field), Toast.LENGTH_SHORT).show()

        }
    }

    private fun checkLoggedInstance() {
        if (FirebaseAuth.getInstance().currentUser == null) {
            Toast.makeText(requireContext(), getString(R.string.you_havent_registered), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), getString(R.string.you_are_registered), Toast.LENGTH_SHORT).show()
            findNavController().navigate(RegisterDirections.actionRegisterToMain())
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}