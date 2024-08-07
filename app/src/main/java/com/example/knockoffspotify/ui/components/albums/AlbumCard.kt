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
fun AlbumCard(
    albums: List<Album>,
    isList: Boolean,
    onAlbumCardClicked: (String) -> Unit,
) {
    AnimatedVisibility(
        visible = !isList,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            items(items = albums) { album ->
                AlbumCardGrid(album = album, onAlbumCardClicked)
            }
        }
    }

    AnimatedVisibility(
        visible = isList,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        LazyColumn {
            items(albums) { album ->
                AlbumCardList(album, onAlbumCardClicked)
            }
        }
    }
}