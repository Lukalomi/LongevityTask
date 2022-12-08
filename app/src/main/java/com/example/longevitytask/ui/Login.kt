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
import com.example.longevitytask.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



@AndroidEntryPoint
class Login : Fragment() {

    private var binding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.btnLogin.setOnClickListener{
            logInUser()
        }
    }

    private fun logInUser() {
        checkFields()
        if (binding!!.etEmail.text.toString().isNotEmpty() && binding!!.etPass.text.toString()
                .isNotEmpty()
        ) {
            viewLifecycleOwner.lifecycleScope.launch {

                try {
                    binding!!.pgLogIn.visibility = View.VISIBLE
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(
                        binding!!.etEmail.text.toString(),
                        binding!!.etPass.text.toString()
                    ).addOnCompleteListener {

                        if (it.isSuccessful) {
                            checkLoggedInstance()
                            binding!!.pgLogIn.visibility = View.INVISIBLE

                        } else {
                            Toast.makeText(
                                requireContext(),
                                it.exception.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                            binding!!.pgLogIn.visibility = View.INVISIBLE

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
                .isEmpty()
        ) {
            Toast.makeText(requireContext(), getString(R.string.fill_every_field), Toast.LENGTH_SHORT)
                .show()

        }
    }

    private fun checkLoggedInstance() {
        if (FirebaseAuth.getInstance().currentUser == null) {
            Toast.makeText(requireContext(), getString(R.string.you_havent_registered), Toast.LENGTH_SHORT).show()
        } else if (FirebaseAuth.getInstance().currentUser !== null) {
            Toast.makeText(requireContext(), getString(R.string.you_logged_in), Toast.LENGTH_SHORT).show()
            findNavController().navigate(
                LoginDirections.actionLoginToMain(

                )
            )

        } else {

            Toast.makeText(requireContext(), getString(R.string.already_logged_in), Toast.LENGTH_SHORT).show()
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}