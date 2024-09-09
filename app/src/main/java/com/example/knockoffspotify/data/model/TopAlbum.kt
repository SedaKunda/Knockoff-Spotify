package com.example.knockoffspotify.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

data class TopAlbums(
    val feed: Feed
)

data class Feed(
    @SerializedName("entry")
    val topAlbums: List<TopAlbum>,
    val id: Id2,
)

@SerialName("id")
data class Id2(
    val label: String,
)

data class TopAlbum(
    @SerializedName("im:name")
    val name: AlbumName,
    @SerializedName("im:image")
    val image: List<AlbumImage>,
    val id: Id,
    @SerializedName("im:artist")
    val artist: Artist,
    val category: Category,
    @SerializedName("im:releaseDate")
    val releaseDate: ReleaseDate,
)

data class AlbumName(
    val label: String,
)

data class AlbumImage(
    val label: String,
    val attributes: Attributes,
)

data class Attributes(
    val height: String,
)

data class Id(
    @SerializedName("attributes")
    val attributes: IdAttributes,
)

@SerialName("attributes")
data class IdAttributes(
    @SerializedName("im:id")
    val imId: String,
)

data class Artist(
    val label: String,
)

data class Category(
    @SerializedName("attributes")
    val attributes: CategoryAttributes,
)

@SerialName("attributes")
data class CategoryAttributes(
    @SerializedName("im:id")
    val id: String,
    val term: String,
)

data class ReleaseDate(
    val label: String,
)