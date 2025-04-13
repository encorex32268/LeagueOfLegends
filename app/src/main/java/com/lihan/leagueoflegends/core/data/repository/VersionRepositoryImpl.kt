package com.lihan.leagueoflegends.core.data.repository

import com.lihan.leagueoflegends.core.domain.network.get
import com.lihan.leagueoflegends.core.domain.repository.VersionRepository
import com.lihan.leagueoflegends.core.domain.sharedpreferences.SharedPreferencesManager
import com.lihan.leagueoflegends.core.domain.util.DataError
import com.lihan.leagueoflegends.core.domain.util.Result
import com.lihan.leagueoflegends.core.domain.util.map
import io.ktor.client.HttpClient

class VersionRepositoryImpl(
    private val httpClient: HttpClient,
    private val sharedPreferencesManager: SharedPreferencesManager
): VersionRepository{
    override suspend fun getVersion(): Result<String, DataError.Network> {
        return httpClient.get<List<String>>(
            route = "https://ddragon.leagueoflegends.com/api/versions.json"
        ).map {
            val version = it.first()
            sharedPreferencesManager.setVersion(version)
            version
        }
    }
}