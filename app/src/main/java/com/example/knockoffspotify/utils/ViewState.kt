package com.example.knockoffspotify.utils

/**
 * Represents the different states of a view.
 * @param T The type of the data that the view will display.
 * @property Loading Represents the loading state of the view.
 * @property Error Represents the error state of the view.
 * @property Success Represents the success state of the view.
 */
sealed class ViewState<out T> {
    data object Loading : ViewState<Nothing>()
    data object Error : ViewState<Nothing>()
    data class Success<out T>(val entries: T) : ViewState<T>()
}