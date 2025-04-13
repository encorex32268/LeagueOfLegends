package com.lihan.leagueoflegends.core.domain.room

import androidx.room.TypeConverter
import com.lihan.leagueoflegends.core.data.local.entity.SpellEntity
import kotlinx.serialization.json.Json

class SpellEntitiesConverter {

    @TypeConverter
    fun convertToString(spellEntities: List<SpellEntity>?): String{
        return Json.encodeToString(spellEntities)
    }

    @TypeConverter
    fun convertToObject(json: String): List<SpellEntity>? {
        return Json.decodeFromString(json)
    }
}