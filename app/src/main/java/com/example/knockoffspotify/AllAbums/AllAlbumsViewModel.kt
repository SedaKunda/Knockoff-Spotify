package com.example.knockoffspotify.AllAbums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knockoffspotify.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllAlbumsViewModel @Inject constructor(val fetchAlbumsFromApi: FetchAlbumsFromApi) : ViewModel() {
    private val _state = MutableStateFlow<ViewState>(ViewState.Loading)
    val state: StateFlow<ViewState> = _state

    fun getAlbums() {
        viewModelScope.launch {
            try {
                val latestAlbums = fetchAlbumsFromApi()
                _state.value = ViewState.Success(entries = latestAlbums)
            }
            catch (e: Exception) {
                _state.value = ViewState.Error
                e.printStackTrace()
            }
        }
    }
}