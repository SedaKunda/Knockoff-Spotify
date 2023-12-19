package com.example.knockoffspotify.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class AlbumEntity(
    val name: String,
    @Embedded val image: Image,
    @ColumnInfo(name = "item_count")
    val itemCount: String,
    val price: String,
    val rights: String,
    val title: String,
    val link: String,
    @PrimaryKey
    val id: String,
    val artist: String,
    val category: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
)

data class Image(
    val label: String,
    @Embedded val attributes: ImageAttributes,
)

data class ImageAttributes(
    val height: String,
)