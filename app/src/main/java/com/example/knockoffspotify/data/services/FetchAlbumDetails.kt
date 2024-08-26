package com.example.knockoffspotify.data.services

import com.example.knockoffspotify.data.remote.AlbumsApiService
import com.example.knockoffspotify.utils.ViewState
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchAlbumDetails @Inject constructor(
    private val service: AlbumsApiService
) {
    fun fetchAlbumDetails(id: String) = flow {
        emit(ViewState.Loading)
        try {
            emit(ViewState.Success(service.getAlbumDetails(id)))
        } catch (exception: Exception) {
            emit(ViewState.Error)
        }
    }
}