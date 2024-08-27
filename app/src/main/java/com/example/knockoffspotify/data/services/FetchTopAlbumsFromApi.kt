package com.example.knockoffspotify.data.services

import com.example.knockoffspotify.data.remote.AlbumsApiService
import com.example.knockoffspotify.utils.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchTopAlbumsFromApi @Inject constructor(private val service: AlbumsApiService) {
    fun fetchAlbums() = flow {
        emit(ViewState.Loading)
        try {
            emit(ViewState.Success(service.getTopAlbums().feed.topAlbum))
        } catch (exception: Exception) {
            emit(ViewState.Error)
        }
    }.flowOn(Dispatchers.IO)
}