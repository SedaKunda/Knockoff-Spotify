package com.example.knockoffspotify.domain.repository

import com.example.knockoffspotify.data.model.Album
import com.example.knockoffspotify.data.model.TopAlbum

interface AlbumsRepository {
    suspend fun getTopAlbums(): List<TopAlbum>

    suspend fun getAlbumDetails(id: String): Album
}