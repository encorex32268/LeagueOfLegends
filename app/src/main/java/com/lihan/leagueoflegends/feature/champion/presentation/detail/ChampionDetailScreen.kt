package com.lihan.leagueoflegends.feature.champion.presentation.detail

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lihan.leagueoflegends.core.domain.util.ObserveAsEvents
import com.lihan.leagueoflegends.core.presentation.CachedImage
import com.lihan.leagueoflegends.core.presentation.LoadingScreen
import com.lihan.leagueoflegends.core.presentation.util.UiEvent
import com.lihan.leagueoflegends.feature.champion.domain.model.ChampionDetail
import com.lihan.leagueoflegends.feature.champion.domain.model.Language
import com.lihan.leagueoflegends.feature.champion.domain.model.Spell
import com.lihan.leagueoflegends.feature.champion.presentation.detail.components.SkinZoomInOutImage
import com.lihan.leagueoflegends.feature.champion.presentation.detail.components.SpellItem
import com.lihan.leagueoflegends.ui.theme.LeagueOfLegendsTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChampionDetailScreenRoot(
    viewModel: ChampionDetailViewModel = koinViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ObserveAsEvents(viewModel.uiEvent) {
        when(it){
            is UiEvent.ErrorMessage -> {
                Toast.makeText(context,it.message, Toast.LENGTH_LONG).show()
                onBack()
            }
        }
    }
    ChampionDetailScreen(
        state = state,
        onBack = onBack
    )
}

@Composable
fun ChampionDetailScreen(
    state: ChampionDetailState,
    onBack: () -> Unit = {}
) {
    var imageModel by remember {
        mutableStateOf<Any?>(null)
    }

    BackHandler(
        onBack = {
            imageModel?.let {
                imageModel = null
            }?: onBack()
        }
    )
    if (state.isLoading){
        LoadingScreen()
    }else{
        CachedImage(
            modifier = Modifier.fillMaxSize().alpha(0.3f),
            key = state.championDetail?.name,
            imageUrl = state.championDetail?.skins?.get(0)?.imageUrl,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ){
                CachedImage(
                    modifier = Modifier.size(70.dp),
                    imageUrl = state.championDetail?.icon,
                    loading = {
                        CircularProgressIndicator()
                    }
                )
                Text(
                    text = state.championDetail?.name?:"",
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = state.championDetail?.title?:"",
                    style = MaterialTheme.typography.labelLarge
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ){
                state.championDetail?.passive?.let { passive ->
                    SpellItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        imageUrl = passive.imageUrl,
                        name = passive.name,
                        description = passive.description
                    )
                }
                state.championDetail?.spells?.forEachIndexed { index, spell ->
                    SpellItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        imageUrl = spell.imageUrl,
                        name = spell.name,
                        description = spell.description,
                        cooldownBurn = spell.cooldownBurn
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
            state.championDetail?.skins?.let { skinsUi ->
                LazyRow(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 100.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    items(skinsUi){ skin ->
                        CachedImage(
                            onClick = {
                                imageModel = skin
                            },
                            imageUrl = skin.imageUrl,
                            loading = {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ){
                                    CircularProgressIndicator()
                                }
                            }
                        )
                    }
                }

            }
        }
        imageModel?.let {
            Dialog(onDismissRequest = { imageModel = null }) {
                SkinZoomInOutImage(
                    model = imageModel,
                    onCloseClick = {
                        imageModel = null
                    }
                )
            }
        }
    }


}

@Composable
@Preview(showSystemUi = true)
fun ChampionDetailScreenPreview(){
    val championDetailPreview = ChampionDetail(
        id = "Zac",
        name = "Zac",
        title = "the Secret Weapon",
        icon = "",
        skins = listOf(),
        passive = null,
        language = Language.US.code,
        spells = listOf(
            Spell(
                name = "Stretching Strikes",
                description = "Zac stretches an arm, grabbing an enemy. Attacking a different enemy will cause him to throw both targets towards each other.",
                cooldownBurn = "14/12.5/11/9.5/8",
                keyboardName = "Q",
                imageUrl = ""
            ),
            Spell(
                name = "Unstable Matter",
                description = "Zac explodes outward towards nearby enemies, dealing a percentage of their maximum health as magic damage.",
                cooldownBurn = "5",
                keyboardName = "W",
                imageUrl = ""
            ),
            Spell(
                name = "Elastic Slingshot",
                description = "Zac attaches his arms to the ground and stretches back, launching himself forward.",
                cooldownBurn = "22/19/16/13/10",
                keyboardName = "E",
                imageUrl = ""
            ),
            Spell(
                name = "Let's Bounce!",
                description = "Zac bounces four times, knocking up enemies hit and slowing them.",
                cooldownBurn = "120/105/90",
                keyboardName = "R",
                imageUrl = ""
            )
        )
    )
    LeagueOfLegendsTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            ChampionDetailScreen(
                state = ChampionDetailState(
                    championDetail = championDetailPreview,
                    isLoading = false
                )
            )
        }
    }
}