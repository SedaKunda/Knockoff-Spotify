package com.example.knockoffspotify.utils

import com.example.knockoffspotify.model.Album

sealed class ViewState {
    data object Loading: ViewState()
    data object Error: ViewState()
    data class Success(val entries: List<Album>): ViewState()
}