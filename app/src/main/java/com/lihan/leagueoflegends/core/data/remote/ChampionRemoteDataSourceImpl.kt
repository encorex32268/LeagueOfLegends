package com.lihan.leagueoflegends.core.data.remote

import com.lihan.leagueoflegends.core.data.remote.model.champion.ChampionsDto
import com.lihan.leagueoflegends.core.domain.network.get
import com.lihan.leagueoflegends.core.domain.sharedpreferences.SharedPreferencesManager
import com.lihan.leagueoflegends.core.domain.util.DataError
import com.lihan.leagueoflegends.core.domain.util.Result
import io.ktor.client.HttpClient

class ChampionRemoteDataSourceImpl(
    private val httpClient: HttpClient,
    private val sharedPreferencesManager: SharedPreferencesManager
): ChampionRemoteDataSource{

    override suspend fun getAllChampions(): Result<ChampionsDto, DataError.Network> {
        val language = sharedPreferencesManager.getLanguage()
        val version = sharedPreferencesManager.getVersion()
        return httpClient.get<ChampionsDto>(
            route = "https://ddragon.leagueoflegends.com/cdn/${version}/data/${language}/champion.json"
        )
    }
}