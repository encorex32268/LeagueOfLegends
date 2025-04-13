package com.lihan.leagueoflegends.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lihan.leagueoflegends.core.data.local.entity.ChampionDetailEntity
import com.lihan.leagueoflegends.core.data.local.entity.ChampionEntity
import com.lihan.leagueoflegends.core.domain.room.PassiveEntityConverter
import com.lihan.leagueoflegends.core.domain.room.SkinEntitiesConverter
import com.lihan.leagueoflegends.core.domain.room.SkinImageUrlsConverter
import com.lihan.leagueoflegends.core.domain.room.SpellEntitiesConverter

@Database(
    entities = [
        ChampionEntity::class,
        ChampionDetailEntity::class
               ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    value = [
        SpellEntitiesConverter::class,
        PassiveEntityConverter::class,
        SkinImageUrlsConverter::class,
        SkinEntitiesConverter::class
    ]
)
abstract class ChampionRoomDatabase: RoomDatabase() {
    abstract val championDao: ChampionDao
    abstract val championDetailDao: ChampionDetailDao
}