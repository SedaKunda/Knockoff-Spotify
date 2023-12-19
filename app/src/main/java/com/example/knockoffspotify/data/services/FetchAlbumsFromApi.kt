package com.example.knockoffspotify.data.services

import com.example.knockoffspotify.data.remote.TopAlbumsApiService
import com.example.knockoffspotify.utils.ViewState
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchAlbumsFromApi @Inject constructor(private val service: TopAlbumsApiService) {
        fun fetchAlbums() = flow {
            emit(ViewState.Loading)
            try {
                emit(ViewState.Success(service.getTopAlbums().feed.album))
            } catch (exception: Exception) {
                emit(ViewState.Error)
            }
    }
}