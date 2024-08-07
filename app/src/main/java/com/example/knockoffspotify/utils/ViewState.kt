package com.example.knockoffspotify.utils

sealed class ViewState<out T> {
    data object Loading : ViewState<Nothing>()
    data object Error : ViewState<Nothing>()
    data class Success<out T>(val entries: T) : ViewState<T>()
}