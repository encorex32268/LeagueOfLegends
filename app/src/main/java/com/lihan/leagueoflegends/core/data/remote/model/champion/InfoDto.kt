package com.lihan.leagueoflegends.core.data.remote.model.champion

import kotlinx.serialization.Serializable

@Serializable
data class InfoDto(
    val attack: Int?,
    val defense: Int?,
    val difficulty: Int?,
    val magic: Int?
)