package com.example.knockoffspotify.ui.album_details

import DefaultErrorScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import coil.compose.AsyncImage
import com.example.knockoffspotify.data.model.Song //todo remove
import com.example.knockoffspotify.domain.model.Album
import com.example.knockoffspotify.ui.components.LoadingItem
import com.example.knockoffspotify.ui.components.topbar.DefaultAppBar
import com.example.knockoffspotify.utils.ViewState

@Composable
fun AlbumDetailsScreen(
    albumId: String,
    albumDetailsViewModel: AlbumDetailsViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            albumDetailsViewModel.getAlbumDetails(albumId)
        }
    }
    val result: ViewState<Album> = albumDetailsViewModel.state.collectAsStateWithLifecycle().value

    Scaffold(
        topBar = {
            DefaultAppBar(onBackPressed)
        },
        content = { padding ->
            AlbumDetailsContent(
                result = result,
                padding = padding
            )
        }
    )
}

@Composable
fun AlbumDetailsContent(
    result: ViewState<Album>,
    padding: PaddingValues
) {
    Surface(
        modifier = Modifier.padding(padding),
        color = MaterialTheme.colorScheme.background,
    ) {
        when (result) {
            ViewState.Error -> DefaultErrorScreen(errorMessage = "Failed to load selected album")
            ViewState.Loading -> LoadingItem()
            is ViewState.Success -> {
                AlbumCard(album = result.entries)
            }
        }
    }
}

@Composable
fun AlbumCard(album: Album) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        item {
            AlbumCardHeader(album.artwork)
        }
        items(album.songs) {
            SongCard(song = it)
        }
    }
}

@Composable
fun AlbumCardHeader(imageUrl: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        AsyncImage(
            model = generateImageURL(imageUrl, 700, 700),
            contentDescription = "album cover",
            modifier = Modifier
                .align(Center)
        )
    }
}

@Composable
fun SongCard(song: Song) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .testTag("SongCard"),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Text(text = song.trackName, style = MaterialTheme.typography.headlineSmall)
        Text(text = song.artistName, style = MaterialTheme.typography.bodyMedium)
    }
}

fun generateImageURL(url: String, width: Int, height: Int): String {
    val desiredSize = "${width}x${height}bb.jpg"
    return url.substringBeforeLast("/") + "/" + desiredSize
}
