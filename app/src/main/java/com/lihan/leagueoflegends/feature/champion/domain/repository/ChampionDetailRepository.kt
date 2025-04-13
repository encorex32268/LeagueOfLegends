package com.lihan.leagueoflegends.feature.champion.domain.repository

import com.lihan.leagueoflegends.feature.champion.domain.model.ChampionDetail
import com.lihan.leagueoflegends.core.domain.util.DataError
import com.lihan.leagueoflegends.core.domain.util.Result

interface ChampionDetailRepository {
    suspend fun getDetail(name: String): Result<ChampionDetail?, DataError.Network>
    suspend fun getDetailFromRoomDatabase(name: String): ChampionDetail?
    suspend fun fetchData(championDetail: ChampionDetail?)
}