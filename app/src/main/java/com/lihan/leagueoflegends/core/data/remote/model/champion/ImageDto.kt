package com.lihan.leagueoflegends.core.data.remote.model.champion

import kotlinx.serialization.Serializable

@Serializable
data class ImageDto(
    val full: String?,
    val group: String?,
    val h: Int?,
    val sprite: String?,
    val w: Int?,
    val x: Int?,
    val y: Int?
)