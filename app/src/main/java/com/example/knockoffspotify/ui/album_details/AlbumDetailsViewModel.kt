package com.example.knockoffspotify.ui.album_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knockoffspotify.data.services.FetchAlbumDetails
import com.example.knockoffspotify.model.Album
import com.example.knockoffspotify.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    private val fetchAlbumDetails: FetchAlbumDetails,
) : ViewModel() {
    private val _state = MutableStateFlow<ViewState<Album>>(ViewState.Loading)
    val state: StateFlow<ViewState<Album>> = _state.asStateFlow()

    fun getAlbumDetails(id: String) {
        viewModelScope.launch {
            fetchAlbumDetails.fetchAlbumDetails(id)
                .catch { e ->
                    _state.value = ViewState.Error
                    e.printStackTrace()
                }
                .collect { viewState ->
                    _state.value = when (viewState) {
                        ViewState.Error -> ViewState.Error
                        ViewState.Loading -> ViewState.Loading
                        is ViewState.Success -> {
                            val filteredResults = viewState.entries.results.filter { it.kind == RecordType.SONG.value }
                            ViewState.Success(Album(filteredResults))
                        }
                    }
                }
        }
    }

    enum class RecordType(val value: String) {
        SONG("song"),
        ALBUM("Album");
    }
}