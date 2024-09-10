package com.example.knockoffspotify.domain.model

/**
 * Represents a song in the domain layer.
 * @param artistName the name of the artist
 * @param trackName the name of the song
 * @param primaryGenreName the genre of the song
 * @param collectionExplicitness the explicitness of the song
 * @param artwork the url of the song artwork
 */
data class Song (
    val artistName: String,
    val trackName: String,
    val primaryGenreName: String,
    val collectionExplicitness: String,
    val artwork: String,
)