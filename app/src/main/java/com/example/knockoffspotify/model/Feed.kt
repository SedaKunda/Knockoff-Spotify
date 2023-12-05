package com.example.knockoffspotify.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

data class TopAlbums(
    val feed: Feed
)

data class Feed(
    val author: Author,
    val entry: List<Entry>,
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

data class Entry(
    @SerializedName("im:name")
    val imName: ImName,
    @SerializedName("im:image")
    val imImage: List<ImImage>,
    @SerializedName("im:itemCount")
    val imItemCount: ImItemCount,
    @SerializedName("im:price")
    val imPrice: ImPrice,
    @SerializedName("im:contentType")
    val imContentType: ImContentType,
    val rights: Rights,
    val title: Title,
    val link: Link,
    val id: Id,
    @SerializedName("im:artist")
    val imArtist: ImArtist,
    val category: Category,
    @SerializedName("im:releaseDate")
    val imReleaseDate: ImReleaseDate,
)

data class ImName(
    val label: String,
)

data class ImImage(
    val label: String,
    val attributes: Attributes,
)

data class Attributes(
    val height: String,
)

data class ImItemCount(
    val label: String,
)

data class ImPrice(
    val label: String,
    val attributes: Attributes2,
)


@SerialName("attributes")
data class Attributes2(
    val amount: String,
    val currency: String,
)

data class ImContentType(
    val imContentType: ImContentType2,
    val attributes: Attributes4,
)

@SerialName("im:contentType")
data class ImContentType2(
    val attributes: Attributes3,
)

@SerialName("attributes")
data class Attributes3(
    val term: String,
    val label: String,
)

@SerialName("attributes")
data class Attributes4(
    val term: String,
    val label: String,
)

data class Rights(
    val label: String,
)

data class Title(
    val label: String,
)

data class Link(
    val attributes: Attributes5,
)

@SerialName("attributes")
data class Attributes5(
    val rel: String,
    val type: String,
    val href: String,
)

data class Id(
    val label: String,
    val attributes: Attributes6,
)

@SerialName("attributes")
data class Attributes6(
    @SerializedName("im:id")
    val imId: String,
)

data class ImArtist(
    val label: String,
    val attributes: Attributes7,
)

@SerialName("attributes")
data class Attributes7(
    val href: String,
)

data class Category(
    val attributes: Attributes8,
)

@SerialName("attributes")
data class Attributes8(
    @SerializedName("im:id")
    val imId: String,
    val term: String,
    val scheme: String,
    val label: String,
)

data class ImReleaseDate(
    val label: String,
    val attributes: Attributes9,
)

@SerialName("attributes")
data class Attributes9(
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

