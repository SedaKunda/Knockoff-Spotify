package com.example.knockoffspotify.domain.repository

import com.example.knockoffspotify.domain.model.Album
import com.example.knockoffspotify.domain.model.TopAlbum

interface AlbumsRepository {
    suspend fun getTopAlbums(): List<TopAlbum>

    suspend fun getAlbumDetails(id: String): Album?
}