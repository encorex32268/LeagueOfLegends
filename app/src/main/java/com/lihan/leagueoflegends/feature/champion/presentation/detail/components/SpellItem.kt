package com.lihan.leagueoflegends.feature.champion.presentation.detail.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.lihan.leagueoflegends.feature.champion.domain.model.Spell

@Composable
fun SpellItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    name: String,
    description: String,
    cooldownBurn: String = ""
) {
    LaunchedEffect(Unit) {
        Log.d("TAG", "SpellItem: ${name}")
        Log.d("TAG", "SpellItem: ${description}")
        Log.d("TAG", "SpellItem: ${imageUrl}")
        Log.d("TAG", "SpellItem: ${cooldownBurn}")
    }
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