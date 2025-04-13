package com.lihan.leagueoflegends.core.domain.room

import androidx.room.TypeConverter
import com.lihan.leagueoflegends.core.data.local.entity.SkinEntity
import com.lihan.leagueoflegends.core.data.local.entity.SpellEntity
import kotlinx.serialization.json.Json

class SkinEntitiesConverter {

    @TypeConverter
    fun convertToString(skinEntities: List<SkinEntity>?): String{
        return Json.encodeToString(skinEntities)
    }

    @TypeConverter
    fun convertToObject(json: String): List<SkinEntity>? {
        return Json.decodeFromString(json)
    }
}