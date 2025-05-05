package com.lihan.leagueoflegends.core.data.remote.model.champion_detail

import kotlinx.serialization.Serializable

@Serializable
data class PassiveDto(
    val name: String?,
    val description: String?,
    val image: InfoImageDto?
)
