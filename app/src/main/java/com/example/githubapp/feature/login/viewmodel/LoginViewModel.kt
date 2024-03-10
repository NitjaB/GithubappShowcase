package com.example.githubapp.feature.login.viewmodel

import com.example.githubapp.core.base.BaseViewModel
import com.example.githubapp.domain.credencials.usecase.ExtractClientCode
import com.example.githubapp.domain.credencials.usecase.GenerateLoginLink
import com.example.githubapp.domain.credencials.usecase.IsAuthLink
import com.example.githubapp.feature.login.models.LoginScreenEvent
import com.example.githubapp.feature.login.models.LoginScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val isAuthLink: IsAuthLink,
    private val extractClientCode: ExtractClientCode,
    generateLoginLink: GenerateLoginLink,
) : BaseViewModel<LoginScreenEvent>() {

    private val authLink = generateLoginLink()

    val state = MutableStateFlow(LoginScreenState(authLink.url))

    override fun onEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.OnRedirect -> {
                if (isAuthLink(event.redirectUrl, authLink)) {
                    val code = extractClientCode(event.redirectUrl)
                    // TODO auth user
                } else {
                    state.update { it.copy(url = event.redirectUrl) }
                }
            }
        }
    }
}