package com.lihan.leagueoflegends.feature.champion.presentation

import android.util.Log
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lihan.leagueoflegends.core.domain.repository.VersionRepository
import com.lihan.leagueoflegends.feature.champion.domain.mapper.toChampionUI
import com.lihan.leagueoflegends.feature.champion.domain.repository.ChampionsRepository
import com.lihan.leagueoflegends.feature.champion.presentation.model.ChampionUi
import com.lihan.leagueoflegends.core.domain.sharedpreferences.SharedPreferencesManager
import com.lihan.leagueoflegends.core.domain.util.Result
import com.lihan.leagueoflegends.core.presentation.util.UiEvent
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChampionsViewModel(
    private val championsRepository: ChampionsRepository,
    private val versionRepository: VersionRepository,
    private val sharedPreferencesManager: SharedPreferencesManager
): ViewModel() {

    private var originChampions = emptyList<ChampionUi>()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _state = MutableStateFlow(ChampionsState())
    val state = _state
        .asStateFlow()
        .onStart {
            dataLoading()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = _state.value
        )

    private fun dataLoading() {
        viewModelScope.launch {
            val version = async { versionRepository.getVersion() }.await()
            Log.d("TAG", "dataLoading: ${version}")
            if (version is Result.Error) {
                _uiEvent.send(
                    UiEvent.ErrorMessage(
                        message = version.error.toString()
                    )
                )
                return@launch
            }
            val localDB = championsRepository.getAllChampionsFromRoom().firstOrNull()
            val userText = _state.value.userText
            if (!localDB.isNullOrEmpty()) {
                val localData = localDB.map {
                    it.toChampionUI() }
                val items = if (userText.isNotEmpty()) {
                    originChampions.filter { championUI ->
                        championUI.name.contains(userText)
                    }
                } else {
                    localData
                }
                originChampions = localData
                _state.update {
                    it.copy(
                        items = items,
                        isLoading = false
                    )
                }
            } else {
                when (val result = championsRepository.getAllChampions()) {
                    is Result.Error -> {
                        _uiEvent.send(
                            UiEvent.ErrorMessage(
                                message = result.error.toString()
                            )
                        )
                        _state.update {
                            it.copy(
                                isLoading = false
                            )
                        }
                    }
                    is Result.Success -> {
                        val remoteData = result.data.champions
                        val items = remoteData.map { it.toChampionUI() }
                        originChampions = items
                        _state.update {
                            it.copy(
                                items = if (userText.isNotEmpty()) {
                                    originChampions.filter { championUI ->
                                        championUI.name.contains(userText)
                                    }
                                } else {
                                    items
                                },
                                isLoading = false
                            )
                        }
                        championsRepository.fetchData(data = remoteData)
                    }
                }
            }
        }
    }


    fun onAction(action: ChampionAction){
        when(action){
            is ChampionAction.OnSearchTextChange -> {
                val userText = action.text
                if (userText.isEmpty()){
                    _state.update {
                        it.copy(
                            userText = userText,
                            items =originChampions
                        )
                    }
                    return
                }
                val searchedData = originChampions.filter {
                    it.name.contains(userText)
                }
                _state.update {
                    it.copy(
                        userText = userText,
                        items = searchedData
                    )
                }

            }

            is ChampionAction.SelectedLanguage -> {
                val languageCode = action.language.code
                sharedPreferencesManager.setLanguage(
                    language = languageCode
                )
                viewModelScope.launch {
                    originChampions = emptyList()
                    _state.update {
                        it.copy(
                            isLoading = true,
                            items = emptyList()
                        )
                    }
                    dataLoading()
                }
            }

            ChampionAction.CloseDialog -> {
                _state.update {
                    it.copy(
                        isShowLanguageDialog = false
                    )
                }
            }
            ChampionAction.ShowDialog -> {
                _state.update {
                    it.copy(
                        isShowLanguageDialog = true
                    )
                }
            }
            is ChampionAction.OnTagSelected -> {
                val tagOfChampions = if (action.tag == "All"){
                    originChampions
                }else{
                    originChampions.filter {
                        action.tag in it.tags
                    }
                }
                _state.update {
                    it.copy(
                        selectedTag = action.tag,
                        items = tagOfChampions
                    )
                }
            }
            else -> Unit
        }
    }
}