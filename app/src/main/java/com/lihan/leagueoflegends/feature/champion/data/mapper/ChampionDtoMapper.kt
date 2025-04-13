package com.lihan.leagueoflegends.feature.champion.data.mapper

import com.lihan.leagueoflegends.feature.champion.data.model.champion.ChampionDto
import com.lihan.leagueoflegends.feature.champion.data.model.champion.ChampionsDto
import com.lihan.leagueoflegends.feature.champion.domain.model.Champion
import com.lihan.leagueoflegends.feature.champion.domain.model.Champions

fun ChampionsDto.toChampions(): Champions {
    return Champions(
        champions = champions.map {
            it.value.toChampion()
        }
    )
}

fun ChampionDto.toChampion(): Champion {
    return Champion(
        id = id?:"",
        name = name?:"",
        title = title?:"",
        blurb = blurb?:"",
        tags = tags?: emptyList()
    )
}