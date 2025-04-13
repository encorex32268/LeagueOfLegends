package com.lihan.leagueoflegends.core.data.sharedpreferences

import android.content.Context
import com.lihan.leagueoflegends.feature.champion.domain.model.Language
import com.lihan.leagueoflegends.core.domain.sharedpreferences.SharedPreferencesManager
import androidx.core.content.edit

class DefaultSharedPreferencesManager(
    private val context: Context
) : SharedPreferencesManager {

    companion object{
        private const val SHARED_PREFERENCE_NAME = "shared_preference"
        private const val LANGUAGE_KEY = "language"
        private const val VERSION_KEY = "version"
    }

    private val sharedPreferences by lazy {
        context.getSharedPreferences(SHARED_PREFERENCE_NAME,Context.MODE_PRIVATE)
    }

    override fun setVersion(version: String) {
        sharedPreferences.edit(commit = true){
            putString(VERSION_KEY, version)
        }
    }

    override fun getVersion(): String {
        return sharedPreferences.getString(VERSION_KEY,"15.3.1")?:"15.3.1"
    }

    override fun setLanguage(language: String) {
        sharedPreferences.edit(commit = true){
            putString(LANGUAGE_KEY, language)
        }
    }
    override fun getLanguage(): String {
        return sharedPreferences.getString(LANGUAGE_KEY,Language.US.code)?:Language.US.code
    }
}