package com.example.longevitytask.ui

import android.app.Dialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.longevitytask.R
import com.example.longevitytask.databinding.FragmentHomeBinding
import com.example.longevitytask.databinding.FragmentLoginBinding
import com.example.longevitytask.domain.model.HomeAnimeModel
import com.example.longevitytask.ui.adapters.AnimeHomeAdapter
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class Home : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var binding: FragmentHomeBinding? = null
    private lateinit var adapter: AnimeHomeAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signOut()
        displayRecentAnimes()
    }


    private fun displayRecentAnimes() {
        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getContentWithFlow().collect {
                    adapter = AnimeHomeAdapter(requireContext())
                    binding!!.rvHome.layoutManager = LinearLayoutManager(activity)
                    binding!!.rvHome.adapter = adapter
                    adapter.submitList(it)
                    adapter.onClickListener = {
                        setRecentAnimesDialog(it)
                    }
                }
            }
        }
    }

    private fun setRecentAnimesDialog(item: HomeAnimeModel.HomeAnimeModelItem) {
        val dialogBinding = layoutInflater.inflate(R.layout.anime_dialog, null)
        val dialog = Dialog(requireContext())
        dialog.setContentView(dialogBinding)
        dialog.setCancelable(true)
        var image: ImageView = dialogBinding.findViewById(R.id.ivDialog)
        val desc = dialogBinding.findViewById<TextView>(R.id.tvDialogDetails)
        val name = dialogBinding.findViewById<TextView>(R.id.tvDialogName)

        desc.text = item.episodeNum
        name.text = item.animeTitle
        Glide.with(requireContext())
            .load(item.animeImg)
            .into(image)

        dialog.show()

    }

    private fun signOut() {
        binding!!.btnSignOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(HomeDirections.actionMainToAuthorization())
            Toast.makeText(requireContext(), getString(R.string.signed_out), Toast.LENGTH_LONG)
                .show()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}