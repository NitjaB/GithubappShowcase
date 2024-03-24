package com.example.githubapp.feature.profile.viewmodel

import android.content.Intent
import androidx.lifecycle.viewModelScope
import com.example.githubapp.core.base.BaseViewModel
import com.example.githubapp.core.base.TIMEOUT_DELAY
import com.example.githubapp.core.navigation.NavigationEvent
import com.example.githubapp.core.navigation.Navigator
import com.example.githubapp.domain.profile.usecase.GetAuthenticatedUsersProfile
import com.example.githubapp.feature.profile.models.ProfileScreenEvent
import com.example.githubapp.feature.profile.models.ProfileScreenEvent.OnReloadClicked
import com.example.githubapp.feature.profile.models.ProfileScreenEvent.OnShareClicked
import com.example.githubapp.feature.profile.models.ProfileScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfile: GetAuthenticatedUsersProfile,
    private val navigator: Navigator,
) : BaseViewModel<ProfileScreenEvent>() {

    private val _viewState = MutableStateFlow(ProfileScreenState())
    val viewState = _viewState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_DELAY),
        initialValue = ProfileScreenState()
    )

    init {
        loadUsersProfile()
    }

    private fun loadUsersProfile() {
        _viewState.update { state ->
            state.copy(isLoading = true, isError = false)
        }
        viewModelScope.launch(Dispatchers.IO) {
            getProfile().fold(
                { profile ->
                    _viewState.update { state ->
                        state.copy(profile = profile, isLoading = false)
                    }
                },
                {
                    _viewState.update { state ->
                        state.copy(
                            isLoading = false,
                            isError = true,
                        )
                    }
                }
            )
        }
    }

    override fun onEvent(event: ProfileScreenEvent) {
        when (event) {
            is OnReloadClicked -> loadUsersProfile()
            OnShareClicked -> _viewState.value.profile?.let {notNullProfile ->
                viewModelScope.launch {
                    navigator.emitDestination(
                        NavigationEvent.OpenExternalDestination(
                            Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, notNullProfile.url )
                                type = "text/plain"
                            }
                        )
                    )
                }
            }
        }
    }
}