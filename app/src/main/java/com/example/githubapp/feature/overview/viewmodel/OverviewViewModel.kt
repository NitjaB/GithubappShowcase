package com.example.githubapp.feature.overview.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.githubapp.core.base.BaseViewModel
import com.example.githubapp.core.navigation.NavigationEvent
import com.example.githubapp.core.navigation.Navigator
import com.example.githubapp.feature.assignedIssues.navigation.AssignedIssuesScreenRouter
import com.example.githubapp.feature.overview.models.OverviewScreenEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val navigator: Navigator,
) : BaseViewModel<OverviewScreenEvents>() {

    override fun onEvent(event: OverviewScreenEvents) {
        when (event) {
            is OverviewScreenEvents.OnIssuesClicked -> viewModelScope.launch {
                navigator.emitDestination(NavigationEvent.Destination(AssignedIssuesScreenRouter.route()))
            }

            else -> {

            }
        }
    }
}
