package com.example.longevitytask.data.remote.fetchapi

import com.example.longevitytask.common.ApiEndPoints
import com.example.longevitytask    .domain.model.HomeAnimeModel
import retrofit2.Response
import retrofit2.http.GET

interface FetchedAnime {
    @GET(ApiEndPoints.getAnimes)
    suspend fun getRecentAnime():Response<HomeAnimeModel>
}