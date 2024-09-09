package com.example.knockoffspotify.domain.usecase

import android.util.Log
import com.example.knockoffspotify.domain.repository.AlbumsRepository
import com.example.knockoffspotify.utils.ViewState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAlbumDetailsUseCase @Inject constructor(
    private val repository: AlbumsRepository
) {
    operator fun invoke(id: String) = flow {
        emit(ViewState.Loading)
        val albumDetails = repository.getAlbumDetails(id)
        emit(ViewState.Success(albumDetails))
    }.catch { exception ->
        emit(ViewState.Error)
        when (exception) {
            is IOException -> Log.e("GetAlbumDetailsUseCase", "Network error", exception)
            else -> Log.e("GetAlbumDetailsUseCase", "Unknown error", exception)
        }
    }

}