package com.example.knockoffspotify.domain.model

/**
 * Represents a top album.
 * @param id unique identifier of the album
 * @param name name of the album
 * @param artist name of the artist
 * @param releaseDate release date of the album
 * @param imageUrl url of the album image
 */
data class TopAlbum(
    val id: String,
    val name: String,
    val artist: String,
    val releaseDate: String,
    val imageUrl: String
)