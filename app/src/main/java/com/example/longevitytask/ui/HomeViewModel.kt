package com.example.longevitytask.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.longevitytask.data.remote.fetchapi.FetchedAnime
import com.example.longevitytask.domain.model.HomeAnimeModel
import com.example.longevitytask.ui.adapters.AnimeHomeAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchedAnime: FetchedAnime
): ViewModel() {
    fun getContentWithFlow(): Flow<List<HomeAnimeModel.HomeAnimeModelItem>> = flow {
        val response = fetchedAnime.getRecentAnime()
        val value: List<HomeAnimeModel.HomeAnimeModelItem>
        if (response.isSuccessful) {
            value = response.body() ?: emptyList()
        }
        else {
            value = emptyList()
        }
        emit(value)
    }
    }