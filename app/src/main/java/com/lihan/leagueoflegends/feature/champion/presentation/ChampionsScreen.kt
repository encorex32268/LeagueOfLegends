package com.lihan.leagueoflegends.feature.champion.presentation

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lihan.leagueoflegends.R
import com.lihan.leagueoflegends.core.domain.util.ObserveAsEvents
import com.lihan.leagueoflegends.core.presentation.LoadingScreen
import com.lihan.leagueoflegends.core.presentation.Translate
import com.lihan.leagueoflegends.core.presentation.util.UiEvent
import com.lihan.leagueoflegends.feature.champion.presentation.components.ChampionList
import com.lihan.leagueoflegends.feature.champion.presentation.components.ChampionSearchBar
import com.lihan.leagueoflegends.feature.champion.presentation.components.LanguageDialog
import com.lihan.leagueoflegends.feature.champion.presentation.components.TagsFilter
import com.lihan.leagueoflegends.feature.champion.presentation.model.ChampionUi
import com.lihan.leagueoflegends.ui.theme.LeagueOfLegendsTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChampionsScreenRoot(
    viewModel: ChampionsViewModel = koinViewModel(),
    onChampionClickGoToDetail: (String) -> Unit = {}
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ObserveAsEvents(viewModel.uiEvent) {
        when(it){
            is UiEvent.ErrorMessage -> {
                Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
            }
        }
    }
    ChampionsScreen(
        state = state,
        onAction = { action ->
            when(action){
                is ChampionAction.OnChampionClick -> {
                    onChampionClickGoToDetail(action.id)
                }
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun ChampionsScreen(
    state: ChampionsState,
    onAction: (ChampionAction) -> Unit = {}
){
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val isHideSearchBar = remember(state.selectedTag){
        state.selectedTag == "All" || state.selectedTag == null
    }
    val isHideFilter = remember(state.userText){
        state.userText.isBlank()
    }
    when{
        state.isShowLanguageDialog -> {
            LanguageDialog(
                onAction = onAction
            )
        }
        state.isLoading -> {
            LoadingScreen()
        }
        else -> {
            Column(
                modifier = Modifier.fillMaxSize().statusBarsPadding(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ){
                AnimatedVisibility(visible = isHideSearchBar) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        ChampionSearchBar(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            hintText = stringResource(R.string.champion_search_bar_hint),
                            text = state.userText,
                            onValueChange = {
                                onAction(ChampionAction.OnSearchTextChange(it))
                            }
                        )
                        IconButton(
                            onClick = {
                                onAction(
                                    ChampionAction.ShowDialog
                                )
                            }
                        ) {
                            Icon(
                                imageVector = Translate,
                                contentDescription = stringResource(R.string.translateicon_content_description)
                            )
                        }

                    }
                }

                AnimatedVisibility(visible = isHideFilter) {
                    TagsFilter(
                        modifier = Modifier.fillMaxWidth(),
                        onTagSelected = {
                            onAction(
                                ChampionAction.OnTagSelected(it)
                            )
                            scope.launch {
                                listState.animateScrollToItem(0)
                            }
                        },
                        selectedTag = state.selectedTag
                    )

                }
                ChampionList(
                    items = state.items,
                    onAction = onAction,
                    listState = listState
                )

            }
        }
    }


}


@Composable
@Preview(showBackground = true)
private fun ChampionsScreenPreview(){
    val itemsPreview = (0..10).map {
        ChampionUi(
            blurb = "Once honored defenders of Shurima against the Void, Aatrox " +
                    "and his brethren would eventually become an even greater " +
                    "threat to Runeterra, and were defeated only by cunning mortal " +
                    "sorcery. But after centuries of imprisonment, Aatrox was the first to find...",
            imageUrl = "Aatrox.png",
            name = "Aatrox",
            title = "the Darkin Blade",
        )
    }
    LeagueOfLegendsTheme {
        ChampionsScreen(
            state = ChampionsState(
                isLoading = false,
                items = itemsPreview
            ),
            onAction = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun ChampionsScreenEmptyPreview(){
    LeagueOfLegendsTheme {
        ChampionsScreen(
            state = ChampionsState(
                isLoading = false,
                items = emptyList()
            ),
            onAction = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun ChampionsScreenLoadingPreview(){
    LeagueOfLegendsTheme {
        ChampionsScreen(
            state = ChampionsState(
                isLoading = true,
                items = emptyList()
            ),
            onAction = {}
        )
    }
}