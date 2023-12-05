package com.example.knockoffspotify.AllAbums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knockoffspotify.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllAlbumsViewModel @Inject constructor(val fetchAlbumsFromApi: FetchAlbumsFromApi) : ViewModel() {
    private val _result = MutableStateFlow<ViewState>(ViewState.Loading)
    val result: StateFlow<ViewState> = _result

    fun getAlbums() {
        viewModelScope.launch {
            try {
                val latestAlbums = fetchAlbumsFromApi()
                _result.value = ViewState.Success(entries = latestAlbums)
            }
            catch (e: Exception) {
                _result.value = ViewState.Error
                e.printStackTrace()
            }
        }
    }
}