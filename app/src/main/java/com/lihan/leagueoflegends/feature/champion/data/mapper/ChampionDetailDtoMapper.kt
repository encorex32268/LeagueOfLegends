package com.lihan.leagueoflegends.feature.champion.data.mapper

import android.util.Log
import com.lihan.leagueoflegends.feature.champion.data.model.champion_detail.ChampionInfoDto
import com.lihan.leagueoflegends.feature.champion.data.model.champion_detail.PassiveDto
import com.lihan.leagueoflegends.feature.champion.data.model.champion_detail.SkinDto
import com.lihan.leagueoflegends.feature.champion.data.model.champion_detail.SpellDto
import com.lihan.leagueoflegends.feature.champion.domain.model.ChampionDetail
import com.lihan.leagueoflegends.feature.champion.domain.model.Passive
import com.lihan.leagueoflegends.feature.champion.domain.model.Skin
import com.lihan.leagueoflegends.feature.champion.domain.model.Spell

fun ChampionInfoDto.toChampionDetail(
    language: String,
    version: String
): ChampionDetail {
    Log.d("TAG", "toChampionDetail: ${version}")
    return ChampionDetail(
        id = id?:"",
        name = name?:"",
        title = title?:"",
        icon = "https://ddragon.leagueoflegends.com/cdn/${version}/img/champion/${id}.png",
        skins = skins?.map {
            it.toSkin(id?:"")
        }?: emptyList(),
        spells = spells?.mapIndexed { index, spellDto ->
            spellDto.toSpell(version = version,index)
        }?: emptyList(),
        language = language,
        passive = passive?.toPassive(version = version)
    )
}

fun SkinDto.toSkin(
    championName: String
): Skin {
    return Skin(
        num = num?:0,
        imageUrl = "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/${championName}_$num.jpg"
    )
}

fun SpellDto.toSpell(
    version: String,
    index: Int
): Spell {
    val newId =  when(index){
        0 -> "Q"
        1 -> "W"
        2 -> "E"
        3 -> "R"
        else -> ""
    }
    return Spell(
        keyboardName = newId,
        name = name?:"",
        description = description?:"",
        cooldownBurn = cooldownBurn?:"",
        imageUrl = "https://ddragon.leagueoflegends.com/cdn/${version}/img/spell/${id}.png"
    )
}

fun PassiveDto.toPassive(
    version: String
): Passive {
    return Passive(
        name = name?:"",
        description = description?:"",
        imageUrl = "https://ddragon.leagueoflegends.com/cdn/${version}/img/passive/${image?.full}"
    )
}
