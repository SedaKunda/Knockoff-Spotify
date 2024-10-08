package com.example.knockoffspotify.ui.albums.album_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knockoffspotify.domain.model.Album
import com.example.knockoffspotify.domain.usecase.GetAlbumDetailsUseCase
import com.example.knockoffspotify.utils.Result
import com.example.knockoffspotify.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    private val getAlbumDetailsUseCase: GetAlbumDetailsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<ViewState<Album>>(ViewState.Loading)
    val state: StateFlow<ViewState<Album>> = _state.asStateFlow()

    fun getAlbumDetails(id: String) {
        viewModelScope.launch {
            getAlbumDetailsUseCase(id)
                .collect { result ->
                    _state.value = when (result) {
                        is Result.Error -> ViewState.Error
                        Result.Loading -> ViewState.Loading
                        is Result.Success -> ViewState.Success(result.data)
                    }
                }
        }
    }
}