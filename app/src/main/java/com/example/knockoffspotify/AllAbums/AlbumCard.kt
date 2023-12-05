package com.example.knockoffspotify.AllAbums

import androidx.compose.foundation.Image
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
import androidx.compose.ui.unit.dp
import com.example.knockoffspotify.R
import com.example.knockoffspotify.model.Entry
import com.example.knockoffspotify.utils.toReadableDate

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
                    text = album.imName.label,
                    modifier = Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = album.imArtist.label,
                    modifier = Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = album.imReleaseDate.label.toReadableDate(),
                    modifier = Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}