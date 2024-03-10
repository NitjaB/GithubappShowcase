package com.example.githubapp.feature.login.viewmodel

import com.example.githubapp.core.base.BaseViewModel
import com.example.githubapp.domain.credencials.usecase.GenerateLoginLink
import com.example.githubapp.feature.login.models.LoginScreenEvent
import com.example.githubapp.feature.login.models.LoginScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    generateLoginLink: GenerateLoginLink,
) : BaseViewModel<LoginScreenEvent>() {

    private val authLink = generateLoginLink()

    val state = MutableStateFlow(LoginScreenState(authLink.url))

    override fun onEvent(event: LoginScreenEvent) {
        TODO("Not yet implemented")
    }
}