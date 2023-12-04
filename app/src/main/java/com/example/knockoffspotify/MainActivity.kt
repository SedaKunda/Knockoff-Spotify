package com.example.knockoffspotify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.knockoffspotify.model.Entry
import com.example.knockoffspotify.ui.theme.KnockoffSpotifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KnockoffSpotifyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var albums = Datasource().loadEntries()
                    AllAlbums(albums, mainViewModel = hiltViewModel())
                }
            }
        }
    }
}

@Composable
fun AllAlbums(albums: List<Entry>, mainViewModel: MainViewModel) {
    val result: ViewState = mainViewModel.result.collectAsState().value

    Scaffold(
        topBar = {},
        content = { padding ->
            Surface(
                modifier = Modifier.padding(padding),
                color = MaterialTheme.colorScheme.background,
            ) {
                when (result){
                    ViewState.Error -> Text(text = "error")
                    ViewState.Loading -> Text(text = "loading")
                    is ViewState.Success -> AlbumList(result.entries)
                }
            }
        }
    )
}

@Composable
private fun AlbumList(albums: List<Entry>) {
    LazyColumn {
        items(albums) { album ->
            AlbumCard(album)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumCard(album: Entry, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        onClick = {},
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Album cover",
                contentScale = ContentScale.Fit,
                modifier = Modifier.padding(16.dp)
            )
            Column {
                Text(
                    text = album.imName?.label.orEmpty(),
                    modifier = Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = album.imArtist?.label.orEmpty(),
                    modifier = Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = album.imReleaseDate?.label.orEmpty(),
                    modifier = Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    KnockoffSpotifyTheme {
//        AllAlbums(Datasource().loadEntries())
//    }
//}

//@Composable
//private fun AlbumList(
//    mainViewModel: MainViewModel,
//) {
//    LaunchedEffect(Unit) {
//        mainViewModel.fetchAllAlbums()
//    }
//    when (val state = mainViewModel.topAlbumsUiState.collectAsState().value) {
//        is MainViewModel.TopAlbumsUiState.Error -> {
//            Text(text = "error")
//        }
//        MainViewModel.TopAlbumsUiState.Loading -> {
//            Text(text = "loading")
//        }
//        is MainViewModel.TopAlbumsUiState.Success -> {
//            LazyColumn {
//                items(state.albums) { album ->
//                    AlbumCard(album)
//                }
//            }
//        }
//    }
//}

