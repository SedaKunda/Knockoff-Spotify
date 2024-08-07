package com.example.knockoffspotify.ui.components.albums

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.knockoffspotify.R
import com.example.knockoffspotify.model.Album
import com.example.knockoffspotify.utils.extractImage
import com.example.knockoffspotify.utils.toReadableDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumCard(
    album: Album,
    onAlbumCardClicked: (String) -> Unit,
    isGrid: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(horizontal = if (isGrid) 4.dp else 8.dp, vertical = if (isGrid) 4.dp else 2.dp)
            .fillMaxWidth()
            .testTag(if (isGrid) "AlbumCardGrid" else "AlbumCardList"),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        onClick = { onAlbumCardClicked(album.id.attributes.imId) },
    ) {
        AlbumCardContent(
            album = album,
            modifier = Modifier.padding(8.dp),
            contentScale = if (isGrid) ContentScale.FillBounds else ContentScale.Fit,
            isGrid = isGrid
        )
    }
}

@Composable
fun AlbumCardContent(
    album: Album,
    modifier: Modifier,
    contentScale: ContentScale,
    isGrid: Boolean
) {
    if (isGrid) {
        Column(modifier = modifier) {
            AsyncImage(
                model = album.imImage.extractImage(),
                modifier = Modifier
                    .height(100.dp)
                    .padding(4.dp),
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                error = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Album cover",
                contentScale = contentScale
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
    } else {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = album.imImage.extractImage(),
                modifier = Modifier
                    .padding(8.dp)
                    .testTag("RowItem"),
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                error = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Album cover",
                contentScale = contentScale
            )
            Column {
                Text(
                    text = album.imName.label,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 8.dp, end = 16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = album.imArtist.label,
                    modifier = Modifier.padding(top = 4.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = album.imReleaseDate.label.toReadableDate(
                        conversionErrorMessage = stringResource(R.string.unknown_release_date)
                    ),
                    modifier = Modifier.padding(top = 4.dp, bottom = 8.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}