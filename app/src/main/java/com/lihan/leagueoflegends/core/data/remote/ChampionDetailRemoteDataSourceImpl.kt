package com.lihan.leagueoflegends.core.data.remote

import com.lihan.leagueoflegends.core.data.remote.model.champion_detail.ChampionDataDto
import com.lihan.leagueoflegends.core.domain.network.get
import com.lihan.leagueoflegends.core.domain.sharedpreferences.SharedPreferencesManager
import com.lihan.leagueoflegends.core.domain.util.DataError
import com.lihan.leagueoflegends.core.domain.util.Result
import io.ktor.client.HttpClient

class ChampionDetailRemoteDataSourceImpl(
    private val httpClient: HttpClient,
    private val sharedPreferencesManager: SharedPreferencesManager
): ChampionDetailRemoteDataSource{

    override suspend fun getDetail(
        name: String
    ): Result<ChampionDataDto?, DataError.Network> {
        val language = sharedPreferencesManager.getLanguage()
        val version = sharedPreferencesManager.getVersion()

        val route = "https://ddragon.leagueoflegends.com/cdn/${version}/data/${language}/champion/${name}.json"
        return httpClient.get<ChampionDataDto>(
            route = route
        )
    }
}