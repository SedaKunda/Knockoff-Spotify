package com.example.knockoffspotify.model

data class Album(
    val results: List<Song>
)

data class Song (
    val wrapperType: String,
    val collectionType: String,
    val artistName: String,
    val collectionName: String,
    val trackName: String,
    val primaryGenreName: String,
    val collectionExplicitness: String,
    val kind: String,
)