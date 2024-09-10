package com.example.knockoffspotify.domain.model

/**
 * Represents an album in the domain layer.
 * @param songs list of songs in the album
 * @param artistName name of the artist
 * @param albumName name of the album
 * @param genre genre of the album
 * @param explicitness explicitness of the album
 * @param artwork url of the album artwork
 */
data class Album(
    val songs: List<Song>,
    val artistName: String,
    val albumName: String,
    val genre: String,
    val explicitness: String,
    val artwork: String
)