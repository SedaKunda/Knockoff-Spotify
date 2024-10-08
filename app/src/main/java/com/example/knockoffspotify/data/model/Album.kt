package com.example.knockoffspotify.data.model

data class Album(
    val results: List<Song>
)

data class Song (
    val wrapperType: String?,
    val collectionType: String?,
    val artistName: String?,
    val collectionName: String?,
    val trackName: String?,
    val primaryGenreName: String?,
    val collectionExplicitness: String?,
    val kind: String?,
    val artworkUrl30: String?,
    val artworkUrl60: String?,
    val artworkUrl100: String?,
)