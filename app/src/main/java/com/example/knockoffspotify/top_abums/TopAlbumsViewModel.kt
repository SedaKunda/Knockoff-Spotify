package com.example.knockoffspotify.top_abums

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knockoffspotify.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopAlbumsViewModel @Inject constructor(
    val fetchAlbumsFromApi: FetchAlbumsFromApi,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow<ViewState>(ViewState.Loading)
    val state: StateFlow<ViewState> = _state

    fun saveState() {
        savedStateHandle.set("liveData", state.value)
    }

    fun getAlbums() {
        viewModelScope.launch {
            try {
                fetchAlbumsFromApi.fetchAlbums().collect { viewState ->
                    _state.emit(
                        when (viewState) {
                            ViewState.Error -> ViewState.Error
                            ViewState.Loading -> ViewState.Loading
                            is ViewState.Success -> {
                                if (viewState.entries.isEmpty()) ViewState.Error
                                else ViewState.Success(entries = viewState.entries)
                            }
                        }
                    )
                }
            } catch (e: Exception) {
                _state.value = ViewState.Error
                e.printStackTrace()
            }
        }
    }
}