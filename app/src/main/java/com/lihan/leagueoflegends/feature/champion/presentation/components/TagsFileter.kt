package com.lihan.leagueoflegends.feature.champion.presentation.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lihan.leagueoflegends.ui.theme.LeagueOfLegendsTheme


private val tagTypes = listOf(
    "All",
    "Fighter",
    "Tank",
    "Mage",
    "Assassin",
    "Marksman",
    "Support",
)

@Composable
fun TagsFilter(
    modifier: Modifier = Modifier,
    tags: List<String> = tagTypes,
    onTagSelected: (String) -> Unit,
    selectedTag: String? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
        
    ){
        tags.forEach { tag ->
            FilterChip(
                border = null,
                shape = RoundedCornerShape(16.dp),
                selected = selectedTag == tag,
                onClick = {
                    onTagSelected(tag)
                },
                label = {
                    Text(
                        text = tag
                    )
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TagsFilterPreview() {
    LeagueOfLegendsTheme {
        TagsFilter(
            onTagSelected = {},
            selectedTag = tagTypes.random()
        )
    }

}