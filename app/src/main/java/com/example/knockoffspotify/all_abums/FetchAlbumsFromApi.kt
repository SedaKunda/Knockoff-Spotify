package com.example.knockoffspotify.all_abums

import com.example.knockoffspotify.data.remote.FeedApiService
import com.example.knockoffspotify.utils.ViewState
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchAlbumsFromApi @Inject constructor(private val service: FeedApiService) {
        fun fetchAlbums() = flow {
            emit(ViewState.Loading)
            try {
                emit(ViewState.Success(service.getTopAlbums().feed.entry))
            } catch (exception: Exception) {
                emit(ViewState.Error)
            }
    }
}