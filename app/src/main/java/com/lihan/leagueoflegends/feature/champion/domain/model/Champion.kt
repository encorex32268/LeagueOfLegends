package com.lihan.leagueoflegends.feature.champion.domain.model

/**
 *  Champion Hero Info
 *
 *  @param id English's Name ex: KaiSa
 *  @param name Character's Name ex: kai'Sa
 *  @param title Character's Nickname ex: Daughter of the Void
 *  @param blurb Description
 *  @param tags Champion's Position ex: [Marksman,Mage]
 *
 *  @property loadingImage ImageUrl ex: $id_0.jpg
 *
 */
data class Champion(
    val id: String,
    val name: String,
    val title: String,
    val blurb: String,
    val tags: List<String> = emptyList(),
){
    val loadingImage: String
        get() = "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/${id}_0.jpg"

}

