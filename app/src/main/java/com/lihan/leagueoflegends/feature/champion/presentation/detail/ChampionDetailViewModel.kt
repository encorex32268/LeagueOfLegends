package com.lihan.leagueoflegends.feature.champion.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.lihan.leagueoflegends.feature.champion.domain.repository.ChampionDetailRepository
import com.lihan.leagueoflegends.core.domain.navigation.Routes
import com.lihan.leagueoflegends.core.presentation.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import com.lihan.leagueoflegends.core.domain.util.Result
import kotlinx.coroutines.flow.update

class ChampionDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: ChampionDetailRepository
): ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _state = MutableStateFlow(ChampionDetailState())
    val state = _state.asStateFlow()


    init {
        viewModelScope.launch {
            val name = savedStateHandle.toRoute<Routes.ChampionDetail>().championName
            if (name.isEmpty()){
                _uiEvent.send(
                    UiEvent.ErrorMessage("Name is Empty")
                )
                return@launch
            }
            val localDB = repository.getDetailFromRoomDatabase(name)
            if (localDB == null){
                viewModelScope.launch {
                    when(val result = repository.getDetail(name)){
                        is Result.Error -> {
                            _uiEvent.send(
                                UiEvent.ErrorMessage(
                                    message = result.error.toString()
                                )
                            )
                        }
                        is Result.Success -> {
                            val resultData = result.data
                            _state.update {
                                it.copy(
                                    championDetail = resultData,
                                    isLoading = false
                                )
                            }
                            resultData?.let {
                                repository.fetchData(
                                    championDetail = it
                                )
                            }
                        }
                    }
                }
            }else{
                _state.update {
                    it.copy(
                        championDetail = localDB,
                        isLoading = false
                    )
                }
            }
        }

    }
}