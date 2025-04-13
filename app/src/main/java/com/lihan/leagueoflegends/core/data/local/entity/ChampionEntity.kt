package com.lihan.leagueoflegends.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ChampionEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val title: String,
    val blurb: String,
    val language: String,
    val tags: String
)


