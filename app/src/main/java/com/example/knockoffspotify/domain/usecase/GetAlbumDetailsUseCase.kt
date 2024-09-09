package com.example.knockoffspotify.domain.usecase

import com.example.knockoffspotify.domain.repository.AlbumsRepository
import com.example.knockoffspotify.utils.ViewState
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAlbumDetailsUseCase @Inject constructor(
    private val repository: AlbumsRepository
) {
    operator fun invoke(id: String) = flow {
        emit(ViewState.Loading)
        try {
            emit(ViewState.Success(repository.getAlbumDetails(id)))
        } catch (exception: Exception) {
            emit(ViewState.Error)
        }
    }
}