package com.lihan.leagueoflegends.core.domain.room

import androidx.room.TypeConverter
import com.lihan.leagueoflegends.core.data.local.entity.PassiveEntity
import kotlinx.serialization.json.Json

class PassiveEntityConverter {

    @TypeConverter
    fun convertToString(passiveEntity: PassiveEntity?): String{
        return Json.encodeToString(passiveEntity)
    }

    @TypeConverter
    fun convertToObject(json: String): PassiveEntity? {
        return Json.decodeFromString(json)
    }
}