package com.lihan.leagueoflegends.core.domain.sharedpreferences


interface SharedPreferencesManager {

    fun setVersion(version: String)
    fun getVersion(): String

    fun setLanguage(language: String)
    fun getLanguage(): String
}