package com.lihan.leagueoflegends.feature.champion.data.repository

import com.lihan.leagueoflegends.core.data.local.ChampionDao
import com.lihan.leagueoflegends.core.data.remote.ChampionRemoteDataSource
import com.lihan.leagueoflegends.feature.champion.data.mapper.toChampion
import com.lihan.leagueoflegends.feature.champion.data.mapper.toChampionEntity
import com.lihan.leagueoflegends.core.domain.network.get
import com.lihan.leagueoflegends.core.domain.sharedpreferences.SharedPreferencesManager
import com.lihan.leagueoflegends.core.domain.util.DataError
import com.lihan.leagueoflegends.core.domain.util.Result
import com.lihan.leagueoflegends.core.domain.util.map
import com.lihan.leagueoflegends.feature.champion.data.mapper.toChampions
import com.lihan.leagueoflegends.core.data.remote.model.champion.ChampionsDto
import com.lihan.leagueoflegends.feature.champion.domain.model.Champion
import com.lihan.leagueoflegends.feature.champion.domain.model.Champions
import com.lihan.leagueoflegends.feature.champion.domain.repository.ChampionsRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ChampionsRepositoryImpl(
    private val championDao: ChampionDao,
    private val remoteDataSource: ChampionRemoteDataSource,
    private val sharedPreferencesManager: SharedPreferencesManager
): ChampionsRepository {

    override suspend fun getAllChampions(): Result<Champions, DataError.Network> {
        return remoteDataSource.getAllChampions().map {
            it.toChampions()
        }
    }

    override fun getAllChampionsFromRoom(): Flow<List<Champion>> {
        val language = sharedPreferencesManager.getLanguage()
        return championDao.getChampions(language).map {
            it.map { entity ->
                entity.toChampion()
            }
        }
    }

    override suspend fun fetchData(data: List<Champion>) {
        val language = sharedPreferencesManager.getLanguage()
        championDao.upsertChampion(
            champion = data.map {
                it.toChampionEntity(language)
            }
        )
    }
}