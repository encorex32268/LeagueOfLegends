package com.lihan.leagueoflegends.feature.champion.presentation.detail

import com.lihan.leagueoflegends.feature.champion.domain.model.ChampionDetail

data class ChampionDetailState(
    val championDetail: ChampionDetail?=null,
    val isLoading: Boolean = true
)
