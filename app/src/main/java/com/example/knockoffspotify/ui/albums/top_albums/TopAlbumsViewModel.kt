package com.example.knockoffspotify.ui.albums.top_albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knockoffspotify.domain.model.TopAlbum
import com.example.knockoffspotify.domain.usecase.GetTopAlbumsUseCase
import com.example.knockoffspotify.utils.Result
import com.example.knockoffspotify.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopAlbumsViewModel @Inject constructor(
    val getTopAlbumsUseCase: GetTopAlbumsUseCase,
) : ViewModel() {

    private var allAlbums: List<TopAlbum> = emptyList()

    private val _state = MutableStateFlow<ViewState<List<TopAlbum>>>(ViewState.Loading)
    val state: StateFlow<ViewState<List<TopAlbum>>> = _state.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    init {
        getAlbums()
    }

    fun getAlbums() {
        viewModelScope.launch {
            getTopAlbumsUseCase().collect { result ->
                _state.value = when (result) {
                    Result.Loading -> ViewState.Loading
                    is Result.Success -> {
                        allAlbums = result.data
                        ViewState.Success(entries = filterAlbums())
                    }
                    is Result.Error -> ViewState.Error
                }
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        _state.value = ViewState.Success(entries = filterAlbums())
    }

    private fun filterAlbums(): List<TopAlbum> {
        return allAlbums.filter { album ->
            album.name.contains(_searchQuery.value, ignoreCase = true)
        }
    }
}
