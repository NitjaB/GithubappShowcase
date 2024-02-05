package com.example.githubapp.feature.search.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.githubapp.core.base.BaseViewModel
import com.example.githubapp.core.base.TIMEOUT_DELAY
import com.example.githubapp.feature.search.model.SearchScreenEvent
import com.example.githubapp.feature.search.model.SearchScreenEvent.onOpenFiltersClicked
import com.example.githubapp.feature.search.model.SearchScreenEvent.onSearchTextChanged
import com.example.githubapp.feature.search.model.SearchScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SearchViewModel : BaseViewModel<SearchScreenEvent>() {

    private val searchText = MutableStateFlow("")

    val state = searchText.map(::SearchScreenState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_DELAY),
            initialValue = SearchScreenState()
        )

    override fun onEvent(event: SearchScreenEvent) {
        when (event) {
            is onSearchTextChanged -> searchText.update { event.text }
            is onOpenFiltersClicked -> {
                // TODO
            }
        }
    }
}
