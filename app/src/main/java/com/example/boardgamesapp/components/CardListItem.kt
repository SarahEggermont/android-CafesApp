package com.example.boardgamesapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.* // ktlint-disable no-wildcard-imports
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.boardgamesapp.Destinations
import com.example.boardgamesapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListItem(
    title: String,
    minPlayTime: Int,
    maxPlayTime: Int,
    minPlayers: Int,
    maxPlayers: Int,
    shortDescription: String,
    thumbnail: String,
    image: String,
    navController: NavController
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    // marge van 16 aan elke kant -> -32 in totaal
    OutlinedCard(
        onClick = { navController.navigate(Destinations.DetailGame.name) },
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier
            .width(screenWidth - 32.dp)
            .padding(0.dp)
    ) {
        Row(
            modifier = Modifier.height(IntrinsicSize.Min)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, top = 6.dp, end = 4.dp, bottom = 6.dp)
                    .width(screenWidth - 32.dp - 16.dp - 4.dp - 100.dp)
            ) {
                Text(text = title, style = MaterialTheme.typography.titleLarge)
                if (minPlayTime == maxPlayTime) {
                    Text(
                        text = "$minPlayTime min " +
                            "($minPlayers - $maxPlayers players)",
                        style = MaterialTheme.typography.labelLarge
                    )
                } else {
                    Text(
                        text = "$minPlayTime - $maxPlayTime min " +
                            "($minPlayers - $maxPlayers players)",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                Text(text = shortDescription, style = MaterialTheme.typography.bodyMedium)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentAlignment = Alignment.CenterEnd
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(thumbnail)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.loading_img),
                    error = painterResource(R.drawable.ic_broken_image),
                    contentDescription = "$title.jpg",
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(100.dp),
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}