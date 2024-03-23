package com.example.githubapp.feature.overview.viewmodel

import com.example.githubapp.core.base.BaseViewModel
import com.example.githubapp.feature.overview.models.OverviewScreenEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor() : BaseViewModel<OverviewScreenEvents>() {

    override fun onEvent(event: OverviewScreenEvents) {

    }
}
