package com.example.knockoffspotify.domain.usecase

import android.util.Log
import com.example.knockoffspotify.domain.repository.AlbumsRepository
import com.example.knockoffspotify.utils.Result
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
        emit(Result.Loading)
        val albumDetails = repository.getAlbumDetails(id)
        if (albumDetails == null) {
            emit(Result.Error(Exception("Album details is empty")))
            Log.e("GetAlbumDetailsUseCase", "Album details is empty")
            return@flow
        }
        emit(Result.Success(albumDetails))
    }.catch { exception ->
        emit(Result.Error(exception))
        when (exception) {
            is IOException -> Log.e("GetAlbumDetailsUseCase", "Network error", exception)
            else -> Log.e("GetAlbumDetailsUseCase", "Unknown error", exception)
        }
    }

}