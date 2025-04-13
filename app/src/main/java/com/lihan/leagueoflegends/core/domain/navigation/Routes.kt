package com.lihan.leagueoflegends.core.domain.navigation

import kotlinx.serialization.Serializable

sealed interface Routes{
    @Serializable
    data object Champions: Routes

    @Serializable
    data class ChampionDetail(
        val championName: String
    ): Routes
}