package com.example.knockoffspotify.domain.usecase

import com.example.knockoffspotify.domain.repository.AlbumsRepository
import com.example.knockoffspotify.utils.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetTopAlbumsUseCase @Inject constructor(
    private val repository: AlbumsRepository
) {
    operator fun invoke() = flow {
        emit(ViewState.Loading)
        try {
            emit(ViewState.Success(repository.getTopAlbums()))
        } catch (exception: Exception) {
            emit(ViewState.Error)
        }
    }.flowOn(Dispatchers.IO)
}