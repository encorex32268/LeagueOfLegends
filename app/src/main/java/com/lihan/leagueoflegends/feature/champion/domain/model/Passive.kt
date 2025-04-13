package com.lihan.leagueoflegends.feature.champion.domain.model

/**
 *   Passive
 *
 *   https://ddragon.leagueoflegends.com/cdn/15.7.1/data/en_US/champion/Kaisa.json
 *   @param name Passive's Name ex: Second Skin
 *   @param description Passive's Description ex: "Kai'Sa's basic attacks..."
 *   @param imageUrl https://ddragon.leagueoflegends.com/cdn/15.7.1/img/passive/${championName}_P.png
 */
data class Passive(
    val name: String = "",
    val description: String = "",
    val imageUrl: String = ""
)
