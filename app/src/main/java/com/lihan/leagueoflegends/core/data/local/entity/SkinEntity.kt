package com.lihan.leagueoflegends.core.data.local.entity

import kotlinx.serialization.Serializable

@Serializable
data class SkinEntity(
    val num: Int,
    val imageUrl: String
)
