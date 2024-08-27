package com.example.knockoffspotify.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

data class TopAlbums(
    val feed: Feed
)

data class Feed(
    @SerializedName("entry")
    val topAlbum: List<TopAlbum>,
    val id: Id2,
)

@SerialName("id")
data class Id2(
    val label: String,
)

