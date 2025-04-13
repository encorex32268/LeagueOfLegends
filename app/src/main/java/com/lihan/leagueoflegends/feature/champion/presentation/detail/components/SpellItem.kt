package com.lihan.leagueoflegends.feature.champion.presentation.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage

@Composable
fun SpellItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    name: String,
    description: String,
    cooldownBurn: String = ""
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        SubcomposeAsyncImage(
            modifier = Modifier.size(48.dp),
            model = imageUrl,
            contentDescription = "Spell ${name}",
            loading = {
                CircularProgressIndicator()
            }
        )
        Column {
            Text(
                text = name,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = description,
                style = MaterialTheme.typography.bodyMedium
            )
            if (cooldownBurn.isNotEmpty()){
                Text(
                    text = "CD:${cooldownBurn}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

    }


}