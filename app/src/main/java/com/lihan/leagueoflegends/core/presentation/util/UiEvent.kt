package com.lihan.leagueoflegends.core.presentation.util

sealed interface UiEvent {
    data class ErrorMessage(
        val message: String
    ): UiEvent
}