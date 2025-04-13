package com.lihan.leagueoflegends.core.data.local.mapper

import com.lihan.leagueoflegends.core.data.local.entity.ChampionEntity
import com.lihan.leagueoflegends.feature.champion.domain.model.Champion
import kotlinx.serialization.json.Json

fun ChampionEntity.toChampion(): Champion {
    return Champion(
        id = id,
        name = name,
        title = title,
        blurb = blurb,
        tags = Json.decodeFromString(tags)
    )
}

fun Champion.toChampionEntity(language: String): ChampionEntity {
    return ChampionEntity(
        id = id,
        name = name,
        title = title,
        blurb = blurb,
        language = language,
        tags = Json.encodeToString(tags)
    )
}