package com.lihan.leagueoflegends.core.data.local.entity

import kotlinx.serialization.Serializable

@Serializable
data class SpellEntity(
    val keyboardName: String,
    val name: String,
    val description: String,
    val cooldownBurn: String,
    val imageUrl: String,
)
