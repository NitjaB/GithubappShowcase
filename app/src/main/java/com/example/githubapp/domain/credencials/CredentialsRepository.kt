package com.example.githubapp.domain.credencials

import com.example.githubapp.data.datasource.CredentialsDatasource
import com.example.githubapp.domain.helpers.toResult
import javax.inject.Inject

class CredentialsRepository @Inject constructor(
    private val datasource: CredentialsDatasource,
) {

    suspend fun login(code: String) =
        datasource.authenticateUser(code).toResult {}
}