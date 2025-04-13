package com.lihan.leagueoflegends.feature.champion.domain.model

data class Spell(
    val keyboardName: String,
    val name: String,
    val description: String,
    val cooldownBurn: String,
    val imageUrl: String,
)
