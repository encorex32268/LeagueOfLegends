package com.lihan.leagueoflegends.core.domain.repository

import com.lihan.leagueoflegends.core.domain.util.DataError
import com.lihan.leagueoflegends.core.domain.util.Result

interface VersionRepository {
    suspend fun getVersion(): Result<String, DataError.Network>
}