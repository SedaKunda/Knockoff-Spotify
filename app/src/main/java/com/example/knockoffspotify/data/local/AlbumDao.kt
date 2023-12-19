package com.example.knockoffspotify.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.knockoffspotify.model.AlbumEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {
    @Query("SELECT * FROM albums")
    fun getAllTopAlbums(): Flow<List<AlbumEntity>>

    @Query("SELECT * FROM albums WHERE id = (:id)")
    fun getById(id: String): AlbumEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(albums: List<AlbumEntity>)
}