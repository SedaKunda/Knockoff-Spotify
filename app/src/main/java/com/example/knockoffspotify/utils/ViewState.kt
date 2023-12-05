package com.example.knockoffspotify.utils

import com.example.knockoffspotify.model.Entry

sealed class ViewState {
    object Loading: ViewState()
    object Error: ViewState()
    data class Success(val entries: List<Entry>): ViewState()
}