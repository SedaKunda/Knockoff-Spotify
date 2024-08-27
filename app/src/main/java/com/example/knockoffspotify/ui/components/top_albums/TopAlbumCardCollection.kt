package com.example.knockoffspotify.ui.components.top_albums

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.knockoffspotify.data.model.TopAlbum

@Composable
fun TopAlbumCardCollection(
    topAlbums: List<TopAlbum>,
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
                items(topAlbums) { album ->
                    TopAlbumCard(album, onAlbumCardClicked, isGrid = false)
                }
            }
        } else {
            LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                items(items = topAlbums) { album ->
                    TopAlbumCard(topAlbum = album, onAlbumCardClicked, isGrid = true)
                }
            }
        }
    }
}