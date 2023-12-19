package com.example.knockoffspotify.di

import android.content.Context
import com.example.knockoffspotify.data.local.AlbumDao
import com.example.knockoffspotify.data.local.AlbumDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context, scope: CoroutineScope): AlbumDatabase =
        AlbumDatabase.getDatabase(context, scope)

    @Provides
    fun provideDao(database: AlbumDatabase): AlbumDao {
        return database.albumDao()
    }
}