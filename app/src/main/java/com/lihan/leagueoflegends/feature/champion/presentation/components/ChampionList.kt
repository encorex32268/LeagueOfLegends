package com.lihan.leagueoflegends.feature.champion.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import com.lihan.leagueoflegends.R
import com.lihan.leagueoflegends.feature.champion.presentation.ChampionAction
import com.lihan.leagueoflegends.feature.champion.presentation.model.ChampionUi

@Composable
fun ChampionList(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    items: List<ChampionUi>,
    onAction: (ChampionAction) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = listState
    ){
        items(
            items = items,
            key = {
                it.name?:""
            }
        ){championUI ->
            ChampionItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        championUI.id?.let {
                            onAction(
                                ChampionAction.OnChampionClick(id = it)
                            )
                        }
                    }
                ,
                title = championUI.title?: stringResource(R.string.loading),
                name = championUI.name?: stringResource(R.string.loading),
                blurb = championUI.blurb?: stringResource(R.string.loading),
                imageUrl = championUI.imageUrl
            )
        }
    }
}