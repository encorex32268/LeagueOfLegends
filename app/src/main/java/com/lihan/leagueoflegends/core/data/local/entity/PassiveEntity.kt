package com.lihan.leagueoflegends.core.data.local.entity

import kotlinx.serialization.Serializable

@Serializable
data class PassiveEntity(
    val name: String?,
    val description: String?,
    val imageUrl: String?=""
)
