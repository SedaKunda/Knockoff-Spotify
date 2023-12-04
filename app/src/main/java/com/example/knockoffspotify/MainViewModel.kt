package com.example.knockoffspotify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knockoffspotify.model.Entry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel (val fetchAlbumsFromApi: FetchAlbumsFromApi) : ViewModel() {
    private val _result = MutableStateFlow<ViewState>(ViewState.Loading)
    val result: StateFlow<ViewState> = _result

    init {
        getAlbums()
    }

    fun getAlbums() {
        viewModelScope.launch {
            val latestAlbums = fetchAlbumsFromApi
            _result.value = ViewState.Success(latestAlbums.invoke())
        }
    }
}