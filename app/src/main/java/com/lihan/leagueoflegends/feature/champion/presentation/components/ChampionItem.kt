package com.lihan.leagueoflegends.feature.champion.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.lihan.leagueoflegends.ui.theme.LeagueOfLegendsTheme

@Composable
fun ChampionItem(
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    name: String,
    title: String,
    blurb: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ){

        Box(
            modifier = Modifier.weight(2f),
            contentAlignment = Alignment.Center
        ){
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .diskCacheKey(name)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                loading = {
                    CircularProgressIndicator()
                }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f)
                .alignByBaseline(),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ){
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = blurb,
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}

@Composable
@Preview(showSystemUi = true)
fun ChampionItemPreview() {
    LeagueOfLegendsTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            ChampionItem(
                imageUrl = null,
                name = "AatroxEEEEE",
                title = "the Darkin Blade",
                blurb = "Once honored defenders of Shurima against the Void, Aatrox and his brethren would eventually become an even greater threat to Runeterra, and were defeated only by cunning mortal sorcery. But after centuries of imprisonment, Aatrox was the first to find...",
                )
        }
    }
}