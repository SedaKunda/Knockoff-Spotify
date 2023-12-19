package com.example.knockoffspotify.utils

import com.example.knockoffspotify.model.Album

sealed interface ViewState {
    object Loading: ViewState
    object Error: ViewState
    data class Success(val entries: List<Album>): ViewState
}