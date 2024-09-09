package com.example.knockoffspotify.di

import com.example.knockoffspotify.data.api.AlbumsApiService
import com.example.knockoffspotify.data.repository.AlbumsRepositoryImpl
import com.example.knockoffspotify.domain.repository.AlbumsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AlbumsModule {
    @Provides
    @Singleton
    fun provideAlbumsRepository(albumsApiService: AlbumsApiService): AlbumsRepository {
        return AlbumsRepositoryImpl(albumsApiService)
    }
}