package com.lihan.leagueoflegends.core.data.remote.model.champion_detail

import kotlinx.serialization.Serializable

@Serializable
data class SpellDto(
    val id: String?,
    val name: String?,
    val description: String?,
    val cooldownBurn: String?,
    val image: InfoImageDto?,
)
