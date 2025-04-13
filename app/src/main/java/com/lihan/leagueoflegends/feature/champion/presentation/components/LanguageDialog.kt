package com.lihan.leagueoflegends.feature.champion.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lihan.leagueoflegends.R
import com.lihan.leagueoflegends.feature.champion.domain.model.Language
import com.lihan.leagueoflegends.feature.champion.presentation.ChampionAction

@Composable
fun LanguageDialog(
    onAction: (ChampionAction) -> Unit
) {
    AlertDialog(
        onDismissRequest = {
            onAction(ChampionAction.CloseDialog)
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onAction(ChampionAction.CloseDialog)
                }
            ) {
                Text(
                    text = stringResource(R.string.alert_dialog_close)
                )
            }
        },
        text = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ){
                items(Language.entries.toList()){ language ->
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                onAction(
                                    ChampionAction.SelectedLanguage(language)
                                )
                                onAction(ChampionAction.CloseDialog)
                            },
                        text = language.code
                    )
                }
            }
        }
    )
}