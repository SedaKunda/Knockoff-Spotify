package com.example.knockoffspotify.ui.components.albums

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.knockoffspotify.model.Album

@Composable
fun AlbumCardCollection(
    albums: List<Album>,
    isList: Boolean,
    onAlbumCardClicked: (String) -> Unit,
) {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        if (isList) {
            LazyColumn {
                items(albums) { album ->
                    AlbumCard(album, onAlbumCardClicked, isGrid = false)
                }
            }
        } else {
            LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                items(items = albums) { album ->
                    AlbumCard(album = album, onAlbumCardClicked, isGrid = true)
                }
            }
        }
    }
}