package com.lihan.leagueoflegends.feature.champion.data.repository

import com.lihan.leagueoflegends.core.data.local.ChampionDetailDao
import com.lihan.leagueoflegends.core.data.local.mapper.toChampionDetail
import com.lihan.leagueoflegends.core.domain.network.get
import com.lihan.leagueoflegends.core.domain.sharedpreferences.SharedPreferencesManager
import com.lihan.leagueoflegends.core.domain.util.DataError
import com.lihan.leagueoflegends.core.domain.util.Result
import com.lihan.leagueoflegends.core.domain.util.map
import com.lihan.leagueoflegends.feature.champion.data.mapper.toChampionDetail
import com.lihan.leagueoflegends.feature.champion.data.model.champion_detail.ChampionDataDto
import com.lihan.leagueoflegends.feature.champion.domain.mapper.toChampionDetailEntity
import com.lihan.leagueoflegends.feature.champion.domain.model.ChampionDetail
import com.lihan.leagueoflegends.feature.champion.domain.repository.ChampionDetailRepository
import io.ktor.client.HttpClient

class ChampionDetailRepositoryImpl(
    private val httpClient: HttpClient,
    private val championDetailDao: ChampionDetailDao,
    private val sharedPreferencesManager: SharedPreferencesManager
): ChampionDetailRepository {

    override suspend fun getDetail(
        name: String
    ): Result<ChampionDetail?, DataError.Network> {
        val language = sharedPreferencesManager.getLanguage()
        val version = sharedPreferencesManager.getVersion()
        val route = "https://ddragon.leagueoflegends.com/cdn/${version}/data/${language}/champion/${name}.json"
        val result = httpClient.get<ChampionDataDto>(
            route = route
        ).map {
            it.champions[name]?.toChampionDetail(
                version = version,
                language = language
            )
        }
        return result
    }

    override suspend fun getDetailFromRoomDatabase(name: String): ChampionDetail? {
        val language = sharedPreferencesManager.getLanguage()
        return championDetailDao.getChampionDetail(
            name = name,
            language = language
        )?.toChampionDetail()
    }

    override suspend fun fetchData(
        championDetail: ChampionDetail?
    ) {
        if (championDetail == null) return
        val language = sharedPreferencesManager.getLanguage()
        championDetailDao.upsertChampion(
            championDetail = championDetail.toChampionDetailEntity(language)
        )
    }

}