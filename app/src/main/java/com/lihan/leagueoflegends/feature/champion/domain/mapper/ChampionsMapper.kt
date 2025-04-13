package com.lihan.leagueoflegends.feature.champion.domain.mapper

import com.lihan.leagueoflegends.feature.champion.domain.model.Champion
import com.lihan.leagueoflegends.feature.champion.presentation.model.ChampionUi


fun Champion.toChampionUI(): ChampionUi {
    return ChampionUi(
        id = id,
        name = name,
        title = title,
        blurb = blurb,
        imageUrl = loadingImage,
        tags = tags,
    )
}


