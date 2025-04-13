package com.lihan.leagueoflegends.feature.champion.domain.model


/**
 *
 *  Champion's Detail
 *
 *  @param id English's Name ex: KaiSa
 *  @param name Character's Name ex: kai'Sa
 *  @param title Character's Title ex: Daughter of the Void
 *  @param icon Character's Icon ex: .../img/champion/KaiSa.png
 *  @param skins Character's Skin List ex : [Skin1, Skin2, Skin3]
 *  @see [Skin]
 *  @param language Language ex: en_US
 *  @see [Language]
 *  @param passive Passive Spell
 *  @see [Passive]
 */

data class ChampionDetail(
    val id: String = "",
    val name: String = "",
    val title: String = "",
    val icon: String = "",
    val skins: List<Skin> = emptyList(),
    val spells: List<Spell> = emptyList(),
    val language: String,
    val passive: Passive?
)
