package com.lihan.leagueoflegends.core.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.lihan.leagueoflegends.core.data.local.entity.ChampionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChampionDao {
    @Query("SELECT * FROM championentity WHERE language=:language")
    fun getChampions(
        language: String
    ): Flow<List<ChampionEntity>>

    @Upsert
    suspend fun upsertChampion(champion: List<ChampionEntity>)

    @Query("DELETE FROM championentity")
    suspend fun deleteAll()

}