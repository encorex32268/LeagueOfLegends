package com.lihan.leagueoflegends.core.domain.room

import androidx.room.TypeConverter
import com.lihan.leagueoflegends.core.data.local.entity.SpellEntity
import kotlinx.serialization.json.Json

class SkinImageUrlsConverter {

    @TypeConverter
    fun convertToString(urls: List<String>?): String{
        return Json.encodeToString(urls)
    }

    @TypeConverter
    fun convertToObject(json: String): List<String>? {
        return Json.decodeFromString(json)
    }
}