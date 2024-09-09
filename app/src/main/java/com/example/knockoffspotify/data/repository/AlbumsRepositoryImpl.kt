package com.example.knockoffspotify.data.repository

import com.example.knockoffspotify.data.api.AlbumsApiService
import com.example.knockoffspotify.data.model.Album
import com.example.knockoffspotify.data.model.TopAlbum
import com.example.knockoffspotify.domain.repository.AlbumsRepository
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(
    private val service: AlbumsApiService
) : AlbumsRepository {
    override suspend fun getTopAlbums(): List<TopAlbum> {
        return service.getTopAlbums().feed.topAlbums
    }

    override suspend fun getAlbumDetails(id: String): Album {
        return service.getAlbumDetails(id)
    }
}