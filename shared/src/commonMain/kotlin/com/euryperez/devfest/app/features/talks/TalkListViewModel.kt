package com.euryperez.devfest.app.features.talks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.euryperez.devfest.app.data.talks.Talk
import com.euryperez.devfest.app.data.talks.TalksRepository
import com.euryperez.devfest.app.data.talks.TalksRepositoryProvider
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TalkListViewModel(
    talksRepository: TalksRepository = TalksRepositoryProvider.talksRepository
) : ViewModel() {

    val viewState: StateFlow<ViewState> = talksRepository.getAllTalksFlow()
        .map { ViewState(talks = it, isLoading = false, isError = false) }
        .catch { emit(ViewState(isLoading = false, isError = true)) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ViewState()
        )

    data class ViewState(
        val talks: List<Talk> = emptyList(),
        val isLoading: Boolean = true,
        val isError: Boolean = false
    )
}