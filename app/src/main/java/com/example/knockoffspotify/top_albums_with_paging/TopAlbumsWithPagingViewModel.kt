package com.example.knockoffspotify.top_albums_with_paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.knockoffspotify.model.Album
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopAlbumsWithPagingViewModel @Inject constructor(private val fetchAlbumsFromApi: FetchAlbumsWithPagingFromApi) : ViewModel() {
    private val _state = MutableStateFlow<PagingData<Album>>(value = PagingData.empty())
    val state: StateFlow<PagingData<Album>> = _state

    fun getAlbums() {
        viewModelScope.launch {
            try {
                fetchAlbumsFromApi.fetchAlbums().collect {
                    _state.value = it
                }
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}