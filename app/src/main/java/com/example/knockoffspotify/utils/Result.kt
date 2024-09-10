package com.example.knockoffspotify.utils

/**
 * A generic class that holds a value with its loading status.
 * @param <T> The type of the data that the business logic will return.
 * @property Loading Represents the loading state of the view.
 * @property Error Represents the error state of the view.
 * @property Success Represents the success state of the view.
 */
sealed class Result<out T> {
    object Loading : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
}
