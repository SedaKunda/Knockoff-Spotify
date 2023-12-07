package com.example.knockoffspotify.all_abums

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.knockoffspotify.R
import com.example.knockoffspotify.model.Entry
import com.example.knockoffspotify.utils.extractImage
import com.example.knockoffspotify.utils.toReadableDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumCard(album: Entry, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        onClick = {},
    ) {
        Row {
            AsyncImage(
                model = album.imImage.extractImage(),
                modifier = Modifier.padding(8.dp),
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                error = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Album cover",
                contentScale = ContentScale.Fit
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
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = album.imReleaseDate.label.toReadableDate(
                        conversionErrorMessage = stringResource(
                            R.string.unknown_release_date
                        )
                    ),
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}