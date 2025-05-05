package com.lihan.leagueoflegends.feature.champion.domain.mapper

import com.lihan.leagueoflegends.core.data.local.entity.ChampionDetailEntity
import com.lihan.leagueoflegends.core.data.local.entity.PassiveEntity
import com.lihan.leagueoflegends.core.data.local.entity.SkinEntity
import com.lihan.leagueoflegends.core.data.local.entity.SpellEntity
import com.lihan.leagueoflegends.feature.champion.domain.model.ChampionDetail
import com.lihan.leagueoflegends.feature.champion.domain.model.Passive
import com.lihan.leagueoflegends.feature.champion.domain.model.Skin
import com.lihan.leagueoflegends.feature.champion.domain.model.Spell


fun ChampionDetail.toChampionDetailEntity(language: String): ChampionDetailEntity {
    return ChampionDetailEntity(
        name = name,
        title = title,
        iconImageUrl = icon,
        spells = spells.map {
            it.toSpellEntity()
        },
        skins = skins.map {
          it.toSkinEntity()
        },
        language = language,
        id = id,
        passive = passive?.toPassiveEntity()
    )
}

fun Passive.toPassiveEntity(): PassiveEntity{
    return PassiveEntity(
        name = name,
        description = description,
        imageUrl = imageUrl
    )
}

fun Skin.toSkinEntity(): SkinEntity {
    return SkinEntity(
        num = num,
        imageUrl = imageUrl
    )
}

fun Spell.toSpellEntity(): SpellEntity{
    return SpellEntity(
        keyboardName = keyboardName,
        name = name,
        description = description,
        cooldownBurn = cooldownBurn,
        imageUrl = imageUrl
    )
}