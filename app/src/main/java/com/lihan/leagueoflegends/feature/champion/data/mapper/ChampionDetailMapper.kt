package com.lihan.leagueoflegends.feature.champion.data.mapper

import com.lihan.leagueoflegends.core.data.local.entity.ChampionDetailEntity
import com.lihan.leagueoflegends.core.data.local.entity.PassiveEntity
import com.lihan.leagueoflegends.core.data.local.entity.SkinEntity
import com.lihan.leagueoflegends.core.data.local.entity.SpellEntity
import com.lihan.leagueoflegends.feature.champion.domain.model.ChampionDetail
import com.lihan.leagueoflegends.feature.champion.domain.model.Passive
import com.lihan.leagueoflegends.feature.champion.domain.model.Skin
import com.lihan.leagueoflegends.feature.champion.domain.model.Spell

fun ChampionDetailEntity.toChampionDetail(): ChampionDetail {
    return ChampionDetail(
        id = id,
        name = name,
        title = title,
        icon = iconImageUrl,
        skins = skins.map { it.toSkin() },
        spells = spells.map { it.toSpell() },
        language = language,
        passive = passive?.toPassive()
    )
}

fun PassiveEntity.toPassive(): Passive {
    return Passive(
        name = name?:"",
        description = description?:"",
        imageUrl = imageUrl?:""
    )
}

fun SpellEntity.toSpell(): Spell{
    return Spell(
        keyboardName = keyboardName,
        name = name,
        description = description,
        cooldownBurn = cooldownBurn,
        imageUrl = imageUrl
    )
}

fun SkinEntity.toSkin(): Skin {
    return Skin(
        num = num,
        imageUrl = imageUrl
    )
}