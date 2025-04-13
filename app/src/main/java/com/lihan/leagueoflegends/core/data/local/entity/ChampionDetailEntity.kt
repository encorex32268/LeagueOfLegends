package com.lihan.leagueoflegends.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lihan.leagueoflegends.feature.champion.domain.model.Passive
import com.lihan.leagueoflegends.feature.champion.domain.model.Spell

@Entity
data class ChampionDetailEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val title: String,
    val iconImageUrl: String,
    val spells: List<SpellEntity>,
    val skins: List<SkinEntity>,
    val language: String,
    val passive: PassiveEntity?
)