package com.example.githubapp.domain.credencials.usecase

import com.example.githubapp.domain.credencials.CredentialsRepository
import javax.inject.Inject

class AuthenticateUser @Inject constructor(
    private val credentialsRepository: CredentialsRepository,
) {

    suspend operator fun invoke(code: String) =
        credentialsRepository.login(code)
}