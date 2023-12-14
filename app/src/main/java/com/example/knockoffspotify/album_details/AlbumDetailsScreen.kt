package com.example.knockoffspotify.album_details

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.knockoffspotify.components.DefaultAppBar

@Composable
fun AlbumDetailsScreen(album: String?) {
    Scaffold(
        topBar = {
            DefaultAppBar()
        },
        content = { padding ->
            Surface(
                modifier = Modifier.padding(padding),
                color = MaterialTheme.colorScheme.background,
            ) {
                Text(text = album ?: "")
            }
        }
    )
}