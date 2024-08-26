package com.example.knockoffspotify.utils

import com.example.knockoffspotify.model.AlbumImage
import com.example.knockoffspotify.model.Attributes
import org.junit.Assert
import org.junit.Test

class ImageUtilsTest {
    @Test
    fun `can get image correctly`() {
        val imImage = listOf(
            AlbumImage(
                label = "https://is1-ssl.mzstatic.com/image/thumb/Music116/",
                attributes = Attributes(height = "55")
            ),
            AlbumImage(
                label = "https://is1-ssl.mzstatic.com/image/thumb/Music116/",
                attributes = Attributes(height = "60")
            ),
            AlbumImage(
                label = "https://is1-ssl.mzstatic.com/image/thumb/Music116/",
                attributes = Attributes(height = "170")
            )
        )

        val extractedImage = imImage.extractImage()

        Assert.assertEquals("https://is1-ssl.mzstatic.com/image/thumb/Music116/", extractedImage)
    }

    @Test
    fun `can return null if image list is empty`() {
        val imImage: List<AlbumImage> = listOf()

        val extractedImage = imImage.extractImage()

        Assert.assertNull(extractedImage)
    }

    @Test
    fun `can return null if image list has only 1 element`() {
        val imImage: List<AlbumImage> = listOf()

        val extractedImage = imImage.extractImage()

        Assert.assertNull(extractedImage)
    }
}