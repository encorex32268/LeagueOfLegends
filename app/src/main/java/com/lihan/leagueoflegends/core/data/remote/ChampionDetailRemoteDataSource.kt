package com.lihan.leagueoflegends.core.data.remote

import com.lihan.leagueoflegends.core.data.remote.model.champion_detail.ChampionDataDto
import com.lihan.leagueoflegends.core.domain.util.DataError
import com.lihan.leagueoflegends.core.domain.util.Result

interface ChampionDetailRemoteDataSource {
    suspend fun getDetail(name: String): Result<ChampionDataDto?, DataError.Network>
}