package com.example.knockoffspotify.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

data class TopAlbums(
    val feed: Feed
)

data class Feed(
    val author: Author,
    @SerializedName("entry")
    val album: List<Album>,
    val updated: Updated,
    val rights: Rights2,
    val title: Title2,
    val icon: Icon,
    val link: List<Link2>,
    val id: Id2,
)

data class Author(
    val name: Name,
    val uri: Uri,
)

data class Name(
    val label: String,
)

data class Uri(
    val label: String,
)

data class Updated(
    val label: String,
)

@SerialName("rights")
data class Rights2(
    val label: String,
)

@SerialName("title")
data class Title2(
    val label: String,
)

data class Icon(
    val label: String,
)

@SerialName("link")
data class Link2(
    val attributes: Attributes10,
)


@SerialName("attributes")
data class Attributes10(
    val rel: String,
    val type: String,
    val href: String,
)

@SerialName("id")
data class Id2(
    val label: String,
)

