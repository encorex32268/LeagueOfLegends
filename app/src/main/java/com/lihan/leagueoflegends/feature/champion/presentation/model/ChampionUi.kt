package com.lihan.leagueoflegends.feature.champion.presentation.model

data class ChampionUi(
    val id: String = "",
    val blurb: String = "",
    val name: String = "",
    val title: String = "",
    val imageUrl: String = "",
    val tags: List<String> = emptyList()
)