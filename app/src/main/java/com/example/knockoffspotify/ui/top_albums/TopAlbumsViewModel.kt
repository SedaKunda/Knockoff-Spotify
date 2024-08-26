package com.example.knockoffspotify.ui.top_albums

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import com.example.knockoffspotify.data.services.FetchTopAlbumsFromApi
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
class TopAlbumsViewModel @Inject constructor(
    val fetchTopAlbumsFromApi: FetchTopAlbumsFromApi,
) : ViewModel() {
    private val _state = MutableStateFlow<ViewState<List<Album>>>(ViewState.Loading)
    val state: StateFlow<ViewState<List<Album>>> = _state.asStateFlow()

    fun getAlbums(lifecycle: Lifecycle) {
        viewModelScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                fetchTopAlbumsFromApi.fetchAlbums()
                    .catch { e ->
                        _state.emit(ViewState.Error)
                        e.printStackTrace()
                    }
                    .collect { viewState ->
                        _state.emit(
                            when (viewState) {
                                ViewState.Error -> ViewState.Error
                                ViewState.Loading -> ViewState.Loading
                                is ViewState.Success -> {
                                    if (viewState.entries.isNotEmpty()) ViewState.Success(entries = viewState.entries)
                                    else ViewState.Error
                                }
                            }
                        )
                    }
            }
        }
    }
}