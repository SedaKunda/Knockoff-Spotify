package com.example.knockoffspotify.domain.usecase

import android.util.Log
import com.example.knockoffspotify.domain.repository.AlbumsRepository
import com.example.knockoffspotify.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Use case to get the top albums
 * @param repository the repository to get the top albums
 * @return a flow of the top albums with the current state of the request or an error
 */
class GetTopAlbumsUseCase @Inject constructor(
    private val repository: AlbumsRepository
) {
    operator fun invoke() = flow {
        emit(Result.Loading)
        val albums = repository.getTopAlbums()
        emit(Result.Success(albums))
    }.catch { exception ->
        emit(Result.Error(exception))
        when (exception) {
            is IOException -> Log.e("GetTopAlbumsUseCase", "Network error", exception)
            is HttpException -> Log.e("GetTopAlbumsUseCase", "HTTP error", exception)
            else -> Log.e("GetTopAlbumsUseCase", "Unknown error", exception)
        }
    }.flowOn(Dispatchers.IO)
}