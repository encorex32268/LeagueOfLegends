package com.lihan.leagueoflegends.feature.champion.domain.model

/**
 *  Skin
 *  @param num Skin's Number
 *  @param imageUrl Skin's Image Url ex: "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/${championName}_$num.jpg"
 */
data class Skin(
    val num: Int,
    val imageUrl: String
)
