package com.example.longevitytask.domain.model

import com.google.gson.annotations.SerializedName

class HomeAnimeModel : ArrayList<HomeAnimeModel.HomeAnimeModelItem>(){
    data class HomeAnimeModelItem(
        val animeId: String?,
        val episodeId: String?,
        val animeTitle: String?,
        val episodeNum: String?,
        val subOrDub: String?,
        val animeImg: String?,
        val episodeUrl: String?
    )
}