package com.example.knockoffspotify.data.local

import androidx.annotation.WorkerThread
import com.example.knockoffspotify.model.AlbumEntity
import kotlinx.coroutines.flow.Flow

// Pass in the DAO instead of the whole database, because you only need access to the DAO
class AlbumRepository(private val albumDao: AlbumDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val topAlbums: Flow<List<AlbumEntity>> = albumDao.getAllTopAlbums()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(users: List<AlbumEntity>) {
        albumDao.insertAll(users)
    }

    @WorkerThread
    suspend fun getById(id: String) {
        albumDao.getById(id)
    }
}