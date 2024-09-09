package com.example.knockoffspotify.ui.albums.album_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knockoffspotify.domain.model.Album
import com.example.knockoffspotify.domain.usecase.GetAlbumDetailsUseCase
import com.example.knockoffspotify.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.knockoffspotify.data.model.Album as ApiAlbum

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    private val getAlbumDetailsUseCase: GetAlbumDetailsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<ViewState<Album>>(ViewState.Loading)
    val state: StateFlow<ViewState<Album>> = _state.asStateFlow()

    fun getAlbumDetails(id: String) {
        viewModelScope.launch {
            getAlbumDetailsUseCase(id)
                .collect { viewState ->
                    _state.value = when (viewState) {
                        ViewState.Error -> ViewState.Error
                        ViewState.Loading -> ViewState.Loading
                        is ViewState.Success -> handleSuccess(viewState)
                    }
                }
        }
    }

    private fun handleSuccess(viewState: ViewState.Success<ApiAlbum>): ViewState<Album> { //todo move ApiAlbum out of viewmodel!!
        val (albums, songs) = viewState.entries.results.partition { it.collectionType == RecordType.ALBUM.value }

        val albumResult = albums.firstOrNull()?.let {
            Album(
                albumName = it.collectionName ?: "",
                artistName = it.artistName ?: "",
                genre = it.primaryGenreName ?: "",
                explicitness = it.collectionExplicitness ?: "",
                artwork = it.artworkUrl60 ?: "",
                songs = songs
            )
        }

        return if (albumResult != null) {
            ViewState.Success(albumResult)
        } else {
            ViewState.Error
        }
    }

    enum class RecordType(val value: String) {
        SONG("song"),
        ALBUM("Album");
    }
}