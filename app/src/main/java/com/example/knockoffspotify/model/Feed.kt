package com.example.knockoffspotify.model

import com.google.gson.annotations.SerializedName

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
    val imName: ImName? = null,
    @SerializedName("im:image")
    val imImage: List<ImImage>? = null,
    @SerializedName("im:itemCount")
    val imItemCount: ImItemCount? = null,
    @SerializedName("im:price")
    val imPrice: ImPrice? = null,
    @SerializedName("im:contentType")
    val imContentType: ImContentType? = null,
    val rights: Rights? = null,
    val title: Title? = null,
    val link: Link? = null,
    val id: Id? = null,
    @SerializedName("im:artist")
    val imArtist: ImArtist? = null,
    val category: Category? = null,
    @SerializedName("im:releaseDate")
    val imReleaseDate: ImReleaseDate? = null,
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

data class Attributes2(
    val amount: String,
    val currency: String,
)

data class ImContentType(
//    @JsonProperty("im:contentType")
    val imContentType: ImContentType2,
    val attributes: Attributes4,
)

data class ImContentType2(
    val attributes: Attributes3,
)

data class Attributes3(
    val term: String,
    val label: String,
)

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

data class Attributes5(
    val rel: String,
    val type: String,
    val href: String,
)

data class Id(
    val label: String,
    val attributes: Attributes6,
)

data class Attributes6(
//    @JsonProperty("im:id")
    val imId: String,
)

data class ImArtist(
    val label: String,
    val attributes: Attributes7? = null,
)

data class Attributes7(
    val href: String,
)

data class Category(
    val attributes: Attributes8,
)

data class Attributes8(
    @SerializedName("im:id")
    val imId: String,
    val term: String,
    val scheme: String,
    val label: String,
)

data class ImReleaseDate(
    val label: String,
    val attributes: Attributes9? = null,
)

data class Attributes9(
    val label: String,
)

data class Updated(
    val label: String,
)

data class Rights2(
    val label: String,
)

data class Title2(
    val label: String,
)

data class Icon(
    val label: String,
)

data class Link2(
    val attributes: Attributes10,
)

data class Attributes10(
    val rel: String,
    val type: String? = null,
    val href: String,
)

data class Id2(
    val label: String,
)

