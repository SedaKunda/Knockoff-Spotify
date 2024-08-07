package com.example.knockoffspotify.ui.components.albums

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.knockoffspotify.R
import com.example.knockoffspotify.model.Album
import com.example.knockoffspotify.utils.extractImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumCardGrid(album: Album, onAlbumCardClicked: (String) -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .testTag("AlbumCardGrid"),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        shape = RoundedCornerShape(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        onClick = {
            onAlbumCardClicked(album.id.label) //todo
        },
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = album.imImage.extractImage(),
                modifier = Modifier
                    .height(100.dp)
                    .padding(4.dp),
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                error = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Album cover",
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = album.imName.label,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = album.imArtist.label,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 4.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}