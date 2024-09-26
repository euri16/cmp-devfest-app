package com.euryperez.devfest.app.features.talkDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.euryperez.devfest.app.data.talks.Talk
import com.euryperez.devfest.app.data.talks.TalksRepository
import com.euryperez.devfest.app.data.talks.TalksRepositoryProvider
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TalkDetailViewModel(
    talkId: String,
    talksRepository: TalksRepository = TalksRepositoryProvider.talksRepository
) : ViewModel() {

    val viewState = talksRepository.getTalkFlow(talkId)
        .map { ViewState(talk = it, isLoading = false, isError = false) }
        .catch { emit(ViewState(isLoading = false, isError = true)) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ViewState()
        )


    data class ViewState(
        val talk: Talk? = null,
        val isLoading: Boolean = true,
        val isError: Boolean = false
    )
}