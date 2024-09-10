package com.example.knockoffspotify.domain.usecase

import android.util.Log
import com.example.knockoffspotify.domain.repository.AlbumsRepository
import com.example.knockoffspotify.utils.ViewState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

/**
 * Use case to get album details
 * @param repository: The repository to fetch the data from.
 * @return A flow of ViewState that contains the album details or an error.
 */
class GetAlbumDetailsUseCase @Inject constructor(
    private val repository: AlbumsRepository
) {
    operator fun invoke(id: String) = flow {
        emit(ViewState.Loading)
        val albumDetails = repository.getAlbumDetails(id)
        if (albumDetails == null) {
            emit(ViewState.Error)
            Log.e("GetAlbumDetailsUseCase", "Album details is empty")
            return@flow
        }
        emit(ViewState.Success(albumDetails))
    }.catch { exception ->
        emit(ViewState.Error)
        when (exception) {
            is IOException -> Log.e("GetAlbumDetailsUseCase", "Network error", exception)
            else -> Log.e("GetAlbumDetailsUseCase", "Unknown error", exception)
        }
    }

}