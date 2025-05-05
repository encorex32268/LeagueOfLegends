package com.lihan.leagueoflegends.core.data.remote.model.champion_detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChampionDataDto(
    val version: String = "",
    @SerialName("data")
    val champions: Map<String , ChampionInfoDto> = emptyMap()
)