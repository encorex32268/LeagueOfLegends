package com.lihan.leagueoflegends.core.data.remote.model.champion_detail

import kotlinx.serialization.Serializable

@Serializable
data class SkinDto(
    val num: Int?,
    val name: String?
)
