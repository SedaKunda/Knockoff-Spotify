package com.example.knockoffspotify.domain.model

data class Album(
    val songs: List<Song>,
    val artistName: String,
    val albumName: String,
    val genre: String,
    val explicitness: String,
    val artwork: String
)