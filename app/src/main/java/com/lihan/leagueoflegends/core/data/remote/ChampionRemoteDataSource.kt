package com.lihan.leagueoflegends.core.data.remote

import com.lihan.leagueoflegends.core.data.remote.model.champion.ChampionsDto
import com.lihan.leagueoflegends.core.domain.util.DataError
import com.lihan.leagueoflegends.core.domain.util.Result

interface ChampionRemoteDataSource {
    suspend fun getAllChampions(): Result<ChampionsDto, DataError.Network>
}